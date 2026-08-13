package com.abhi.amplify_store.presentation.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhi.amplify_store.domain.usecase.LoginUseCase
import com.abhi.amplify_store.domain.usecase.RegisterUseCase
import com.abhi.amplify_store.domain.usecase.util.Result
import com.abhi.amplify_store.presentation.authentication.util.AuthUiEvent
import com.abhi.amplify_store.presentation.authentication.util.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
   // private val sslValidateUseCase: SSLValidateUseCase,
) : ViewModel() {

    // Skips duplicates - what does the screen look now
    private val _authUiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val authUiState: StateFlow<AuthUiState> = _authUiState

    // Won't skip duplicates - consumed once emitted
    private val _uiEvent = Channel<AuthUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authUiState.value = AuthUiState.Loading
            try {
                when (val result = loginUseCase(email, password)) {
                    is Result.Success -> {
                        _uiEvent.send(AuthUiEvent.LoginSuccess(result.data))
                    }

                    is Result.Error -> {
                        _uiEvent.send(AuthUiEvent.ShowSnackBar(result.message))
                    }
                }

            } catch (e: Exception) {
                _uiEvent.send(AuthUiEvent.ShowSnackBar(e.message ?: "Login Failed"))

            } finally {
                _authUiState.value = AuthUiState.Idle
            }
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _authUiState.value = AuthUiState.Loading
            try {
                when (val result =
                    registerUseCase(email = email, password = password)) {
                    is Result.Success -> {
                        _uiEvent.send(AuthUiEvent.RegisterSuccess(result.data))
                    }

                    is Result.Error -> {
                        _uiEvent.send(AuthUiEvent.ShowSnackBar(result.message))
                    }
                }
            } catch (e: Exception) {
                _uiEvent.send(AuthUiEvent.ShowSnackBar(e.message ?: "Registration Failed"))
            } finally {
                _authUiState.value = AuthUiState.Idle
            }
        }
    }

//    fun testSSL() {
//        viewModelScope.launch {
//            try {
//                when (val result = sslValidateUseCase.invoke()) {
//                    is Result.Success -> {
//                        Log.d(LOGS, "testSSL: ${result.data}")
//                    }
//                    is Result.Error -> {
//                        Log.d("TrackInfo Logs", "testSSL: ${result.message}")
//                    }
//                }
//            } catch (e: Exception) {
//
//            }
//        }
//    }
}
