package com.abhi.trackinfo.domain.usecase.util

import com.abhi.trackinfo.domain.model.User

sealed interface SSLResult {
    data class Success(val data: String) : SSLResult
    data class Error(val message: String) : SSLResult
}
