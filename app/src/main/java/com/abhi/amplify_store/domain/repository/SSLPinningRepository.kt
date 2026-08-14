package com.abhi.amplify_store.domain.repository

interface SSLPinningRepository {
    suspend fun executeSsl(): String
}
