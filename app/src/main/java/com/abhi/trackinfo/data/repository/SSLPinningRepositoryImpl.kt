package com.abhi.trackinfo.data.repository

import com.abhi.trackinfo.data.remote.SSLApiService
import com.abhi.trackinfo.domain.repository.SSLPinningRepository
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
