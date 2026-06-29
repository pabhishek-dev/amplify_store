package com.abhi.trackinfo.domain.repository

interface SSLPinningRepository {
    suspend fun executeSsl(): String
}
