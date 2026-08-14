package com.abhi.amplify_store.domain.usecase

import com.abhi.amplify_store.domain.repository.SSLPinningRepository
import com.abhi.amplify_store.domain.usecase.util.Result

class SSLValidateUseCase(
    private val sslPinningRepository: SSLPinningRepository
) {
    suspend operator fun invoke(): Result<String> {
        val result = sslPinningRepository.executeSsl()
        return Result.Success(result)
    }
}
