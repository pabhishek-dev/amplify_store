package com.abhi.amplify_store.domain.usecase.util

sealed interface SSLResult {
    data class Success(val data: String) : SSLResult
    data class Error(val message: String) : SSLResult
}
