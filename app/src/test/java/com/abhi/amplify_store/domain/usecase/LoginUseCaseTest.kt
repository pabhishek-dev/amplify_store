package com.abhi.amplify_store.domain.usecase

import com.abhi.amplify_store.domain.model.User
import com.abhi.amplify_store.domain.repository.AuthRepository
import com.abhi.amplify_store.domain.usecase.util.EmailValidator
import com.abhi.amplify_store.domain.usecase.util.PasswordValidator
import com.abhi.amplify_store.domain.usecase.util.Result
import com.abhi.amplify_store.presentation.authentication.AuthenticationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LoginUseCaseTest {

    private lateinit var loginUseCase: LoginUseCase
    private lateinit var registerUseCase: RegisterUseCase
    private lateinit var fakeAuthRepository: FakeAuthRepository
    private lateinit var viewModel: AuthenticationViewModel

    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
        fakeAuthRepository = FakeAuthRepository()

        val emailValidator = EmailValidator()
        val passwordValidator = PasswordValidator()

        loginUseCase = LoginUseCase(fakeAuthRepository, emailValidator, passwordValidator)
        registerUseCase = RegisterUseCase(fakeAuthRepository, emailValidator, passwordValidator)

        viewModel = AuthenticationViewModel(loginUseCase, registerUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `invalid email scenario`() = runTest{
        val result = loginUseCase(
            email = "not-valid-email",
            password = "123456"
        )
        assertTrue(result is Result.Error)
        assertEquals("Enter a valid email id", (result as Result.Error).message)
        assertEquals(0, fakeAuthRepository.loginCallCount)
    }

    @Test
    fun `invalid password`() = runTest {
        val result = loginUseCase(email = "abc@gmail.com", password = "12")
        assertTrue(result is Result.Error)
        assertEquals("Password is not valid", (result as Result.Error).message)
        assertEquals(0, fakeAuthRepository.loginCallCount)
    }

    @Test
    fun `correct email and password`() = runTest {
        val expected = loginUseCase("abc@gmail.com","abhishek")
        assertTrue(expected is Result.Success)

        val expectedResult = User(id=1, email="abc@gmail.com", password="abhishek")
        val result = Result.Success(data = expectedResult)

        assertEquals(expected, result)
    }

    enum class AuthTestScenario {
        SUCCESS,
        INVALID_CREDENTIALS,
        EMAIL_ALREADY_EXISTS,
        NETWORK_ERROR
    }

    private class FakeAuthRepository: AuthRepository {

        var currentScenario = AuthTestScenario.SUCCESS

        var loginCallCount: Int = 0
            private set

        override suspend fun login(
            email: String,
            password: String
        ): User {
            loginCallCount++
            return when(currentScenario) {
                AuthTestScenario.SUCCESS -> {
                    User(id = 1, email = email, password = password)
                }
                AuthTestScenario.INVALID_CREDENTIALS -> {
                    throw Exception("Invalid email or password")
                }
                AuthTestScenario.NETWORK_ERROR -> {
                    throw Exception("Server timeout, try again later")
                }
                AuthTestScenario.EMAIL_ALREADY_EXISTS -> {
                    throw Exception("Unexpected error")
                }
            }
        }

        override suspend fun register(
            email: String,
            password: String
        ): User {
            return when (currentScenario) {
                AuthTestScenario.SUCCESS -> {
                    User(id = 2, email = email, password = password)
                }
                AuthTestScenario.EMAIL_ALREADY_EXISTS -> {
                    throw Exception("Email is already in use")
                }
                AuthTestScenario.NETWORK_ERROR -> {
                    throw Exception("Server timeout, try again later")
                }
                AuthTestScenario.INVALID_CREDENTIALS -> {
                    throw Exception("Password is too weak")
                }
            }
        }
    }
}
