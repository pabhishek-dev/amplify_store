package com.abhi.trackinfo.data.repository

import android.util.Log
import com.abhi.trackinfo.data.local.dao.ProductDao
import com.abhi.trackinfo.data.local.entities.toDomainProduct
import com.abhi.trackinfo.data.model.SSEEvent
import com.abhi.trackinfo.data.model.toDomainProduct
import com.abhi.trackinfo.data.util.LOGS
import com.abhi.trackinfo.data.util.mapper.toEntity
import com.abhi.trackinfo.di.Qualifiers
import com.abhi.trackinfo.domain.model.Product
import com.abhi.trackinfo.domain.repository.HomeRepository
import com.google.gson.Gson
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
                        val sseEvent = gson.fromJson(data, SSEEvent::class.java)
                        trySend(sseEvent)
                    }

                    override fun onFailure(
                        eventSource: EventSource,
                        t: Throwable?,
                        response: Response?
                    ) {
                        close(t ?: Exception("SSE connection failed"))
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
        when (sseEvent.action) {

            "delete" -> sseEvent.product?.let {
                productDao.deleteProduct(it.id)
            } ?: Log.w(LOGS, "Product id not found")

            "delete_all" -> productDao.deleteAllProduct()

            else -> sseEvent.product?.let { homeResponse ->
                productDao.upsertProduct(homeResponse.toDomainProduct().toEntity())
            }
        }
    }
}
