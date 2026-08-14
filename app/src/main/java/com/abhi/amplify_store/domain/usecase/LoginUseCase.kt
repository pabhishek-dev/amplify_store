package com.abhi.amplify_store.domain.usecase

import com.abhi.amplify_store.domain.model.User
import com.abhi.amplify_store.domain.repository.AuthRepository
import com.abhi.amplify_store.domain.usecase.util.EmailValidator
import com.abhi.amplify_store.domain.usecase.util.PasswordValidator
import com.abhi.amplify_store.domain.usecase.util.ValidationMessages.EMAIL_VALIDATION_MSG
import com.abhi.amplify_store.domain.usecase.util.ValidationMessages.PASSWORD_VALIDATION_MSG
import com.abhi.amplify_store.domain.usecase.util.Result

class LoginUseCase(
    private val authRepository: AuthRepository,
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<User> {
        if (!emailValidator.isValidEmail(email)) {
            return Result.Error(EMAIL_VALIDATION_MSG)
        }
        if (!passwordValidator.isPasswordValid(password)) {
            return Result.Error(PASSWORD_VALIDATION_MSG)
        }
        return try {
            val user = authRepository.login(email, password)
            Result.Success(user)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Login failed")
        }
    }
}
