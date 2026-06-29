package com.abhi.trackinfo.domain.usecase

import com.abhi.trackinfo.domain.repository.SSLPinningRepository
import com.abhi.trackinfo.domain.usecase.util.Result

class SSLValidateUseCase(
    private val sslPinningRepository: SSLPinningRepository
) {
    suspend operator fun invoke(): Result<String> {
        val result = sslPinningRepository.executeSsl()
        return Result.Success(result)
    }
}
