package com.abhi.amplify_store.data.repository

import com.abhi.amplify_store.data.local.dao.ProductDao
import com.abhi.amplify_store.data.local.entities.toDomainProduct
import com.abhi.amplify_store.data.model.SSEEvent
import com.abhi.amplify_store.data.model.toDomainProduct
import com.abhi.amplify_store.data.util.LOGS
import com.abhi.amplify_store.data.util.mapper.toEntity
import com.abhi.amplify_store.di.Qualifiers
import com.abhi.amplify_store.domain.model.Product
import com.abhi.amplify_store.domain.repository.HomeRepository
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources
import timber.log.Timber

class HomeRepositoryImpl(
    @param:Qualifiers.SSEClient
    private val sseClient: OkHttpClient,
    private val productDao: ProductDao,
    private val request: Request,
    private val gson: Gson
) : HomeRepository {
    override fun observeProducts(): Flow<List<Product>> {
        return productDao.observeAllProducts().map { entities ->
            entities.map { it.toDomainProduct() }
        }
    }

    override suspend fun connectStream() {

        callbackFlow {
            val eventSource = EventSources.createFactory(sseClient)
                .newEventSource(request, object : EventSourceListener() {
                    override fun onEvent(
                        eventSource: EventSource,
                        id: String?,
                        type: String?,
                        data: String
                    ) {
                        val sseEvent = parseSseEvent(data) ?: return
                        trySend(sseEvent)
                    }

                    override fun onFailure(
                        eventSource: EventSource,
                        t: Throwable?,
                        response: Response?
                    ) {
                        val responseMessage = response?.let {
                            " HTTP ${it.code} ${it.message}, contentType=${it.header("Content-Type")}"
                        }.orEmpty()
                        close(t ?: Exception("SSE connection failed.$responseMessage"))
                    }

                    override fun onClosed(eventSource: EventSource) {
                        close()
                    }
                })
            awaitClose { eventSource.cancel() }
        }
            .onEach { sSEEvent -> handleSseEvent(sSEEvent) }
            .collect()
    }

    private suspend fun handleSseEvent(sseEvent: SSEEvent) {
        try {
            when (sseEvent.action) {
                "initial" -> {
                    sseEvent.products?.let { productDto ->
                        val entities = productDto.map { it.toDomainProduct().toEntity() }

                        // Clear outdated cache and insert full initial snapshot
                        productDao.deleteAllProduct()
                        productDao.addProducts(entities)
                    } ?: Timber.tag(LOGS).w("Initial snapshot payload had no products")
                }

                "insert", "update" -> {
                    sseEvent.product?.let { productDto ->
                        productDao.upsertProduct(productDto.toDomainProduct().toEntity())
                    } ?: Timber.tag(LOGS).w("Missing product payload for ${sseEvent.action}")
                }

                "delete" -> {
                    val idToDelete = sseEvent.productId ?: sseEvent.product?.id
                    idToDelete?.let { id ->
                        productDao.deleteProduct(id)
                    } ?: Timber.tag(LOGS).w("Product ID not found for delete event")
                }

                "delete_all" -> {
                    productDao.deleteAllProduct()
                }

                else -> {
                    Timber.tag(LOGS).w("Unhandled SSE action type: ${sseEvent.action}")
                }
            }
        } catch (e: Exception) {
            Timber.tag(LOGS).e(e, "Error handling SSE event")
        }
    }

    private fun parseSseEvent(data: String): SSEEvent? {
        val trimmedData = data.trim()
        if (!trimmedData.startsWith("{")) {
            Timber.tag(LOGS).w("Ignoring non-JSON-object SSE data: $trimmedData")
            return null
        }

        return try {
            gson.fromJson(trimmedData, SSEEvent::class.java)
        } catch (e: JsonSyntaxException) {
            Timber.tag(LOGS).e(e, "Invalid SSE data: $trimmedData")
            null
        }
    }
}
