package com.abhi.amplify_store.domain.usecase

import com.abhi.amplify_store.domain.model.LoginResponse
import com.abhi.amplify_store.domain.repository.LoginRepository
import com.abhi.amplify_store.domain.usecase.util.EmailValidator
import com.abhi.amplify_store.domain.usecase.util.PasswordValidator
import com.abhi.amplify_store.domain.usecase.util.ValidationMessages.EMAIL_VALIDATION_MSG
import com.abhi.amplify_store.domain.usecase.util.ValidationMessages.PASSWORD_VALIDATION_MSG
import com.abhi.common.Result

class LoginUseCase(
    private val loginRepository: LoginRepository,
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<LoginResponse> {
        if (!emailValidator.isValidEmail(email)) {
            return Result.Error(EMAIL_VALIDATION_MSG)
        }
        if (!passwordValidator.isPasswordValid(password)) {
            return Result.Error(PASSWORD_VALIDATION_MSG)
        }
        return loginRepository.login(email, password)
    }
}
