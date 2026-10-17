package com.abhi.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import com.abhi.common.Result

sealed interface NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>
    data class Error(val code: Int, val message: String) : NetworkResult<Nothing>
    data class Exception(val throwable: Throwable) : NetworkResult<Nothing>
}

suspend fun <T, R> SafeApiCall(
    //dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T,
    mapper: (T)-> R
): Result<R>{
    return withContext(Dispatchers.IO) {
        try {
            val response = apiCall()
            Result.Success(data = mapper(response))
        } catch (e: HttpException){
            Result.Error(e.message())
        } catch (e: IOException) {
            Result.Error(e.message)
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }
}