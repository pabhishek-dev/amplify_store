package com.abhi.trackinfo.domain.repository

import com.abhi.trackinfo.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    // Observes the products emitted from ROOM DB

    fun observeProducts(): Flow<List<Product>>

    // Used to connect to the stream and listen to events

    suspend fun connectStream()

}
