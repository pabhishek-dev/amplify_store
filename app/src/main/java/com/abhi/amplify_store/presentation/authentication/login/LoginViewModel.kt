package com.abhi.amplify_store.presentation.authentication.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhi.amplify_store.domain.usecase.LoginUseCase
import com.abhi.amplify_store.domain.usecase.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _effects = Channel<LoginUiEffect>()
    val effects = _effects.receiveAsFlow()

    fun onEmailChanged(email: String) = _uiState.update { it.copy(email = email) }
    fun onPasswordChanged(password: String) = _uiState.update { it.copy(password = password) }
    fun onAgreementChanged(agreed: Boolean) = _uiState.update { it.copy(agreement = agreed) }
    fun onRememberMeChanged(remember: Boolean) = _uiState.update { it.copy(rememberMe = remember) }

    fun onEvent(event: LoginUiEvent){
        when(event){
            is LoginUiEvent.OnLogInClick -> {

                if(uiState.value.isLoading) return

                login()
            }

            LoginUiEvent.NavigateToForgotPassword -> {}
            LoginUiEvent.NavigateToRegister -> {}
        }
    }

    private fun login(){
        viewModelScope.launch {
            val currentState = _uiState.value
            _uiState.update { it.copy(isLoading = true) }
            try {
                when(val result = loginUseCase(currentState.email, currentState.password)){
                    is Result.Success -> {
                        _effects.send(LoginUiEffect.NavigateToHome(loginResponse = result.data))
                    }
                    is Result.Error -> {
                        _effects.send(LoginUiEffect.ShowSnackBar(result.message))
                    }
                }
            } catch (e: Exception) {
                _effects.send(LoginUiEffect.ShowSnackBar(e.message ?: "Login failed"))
            }

            _uiState.value = LoginUiState(isLoading = false)
        }
    }

}