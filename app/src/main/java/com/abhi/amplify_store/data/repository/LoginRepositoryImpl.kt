package com.abhi.amplify_store.data.repository

import com.abhi.amplify_store.data.model.LoginRequestDto
import com.abhi.amplify_store.domain.model.LoginResponse
import com.abhi.amplify_store.data.remote.LoginApiService
import com.abhi.amplify_store.data.util.mapper.toDomain
import com.abhi.amplify_store.domain.repository.LoginRepository
import com.abhi.amplify_store.domain.usecase.util.Result
import okio.IOException
import retrofit2.HttpException

class LoginRepositoryImpl(
    private val loginApiService: LoginApiService
): LoginRepository {
    override suspend fun login(email: String, password: String): Result<LoginResponse> {
        return try {
            val dto = loginApiService.login(LoginRequestDto(email, password))
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