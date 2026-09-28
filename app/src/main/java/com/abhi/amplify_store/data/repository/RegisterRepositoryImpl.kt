package com.abhi.amplify_store.data.repository

import com.abhi.amplify_store.data.model.RegisterRequestDto
import com.abhi.amplify_store.data.remote.RegisterApiService
import com.abhi.amplify_store.data.util.mapper.toDomain
import com.abhi.amplify_store.domain.model.RegisterResponse
import com.abhi.amplify_store.domain.repository.RegisterRepository
import com.abhi.amplify_store.domain.usecase.util.Result
import okio.IOException
import retrofit2.HttpException

class RegisterRepositoryImpl(
    private val registerApiService: RegisterApiService
): RegisterRepository {
    override suspend fun register(email: String, password: String): Result<RegisterResponse> {
        return try {
           val dto =  registerApiService.register(RegisterRequestDto(email, password))
            Result.Success(dto.toDomain())
        } catch (e: HttpException) {
            // Handle HTTP errors (e.g. 401 Unauthorized, 500 Server Error)
            Result.Error(e.localizedMessage ?: "Invalid credentials")
        } catch (e: IOException) {
            // Handle network failure/no connection
            Result.Error(e.localizedMessage ?: "No internet connection")
        } catch (e: Exception) {
            Result.Error(e.message ?: "An unexpected error occurred")
        }
    }
}