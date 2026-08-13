package com.abhi.amplify_store.data.repository

import com.abhi.amplify_store.data.remote.SSLApiService
import com.abhi.amplify_store.domain.repository.SSLPinningRepository
import javax.net.ssl.SSLPeerUnverifiedException

class SSLPinningRepositoryImpl(
    private val sslApiService: SSLApiService
) : SSLPinningRepository {
    override suspend fun executeSsl(): String {
        return try {
            val response = sslApiService.getData()
            response.message
        } catch (e: SSLPeerUnverifiedException) {
            "SSL Pinning failed: certificate mismatch"
        } catch (e: Exception) {
            "Request failed: ${e.message}"
        }
    }
}
