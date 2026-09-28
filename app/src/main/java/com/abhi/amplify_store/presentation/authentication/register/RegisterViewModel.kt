package com.abhi.amplify_store.presentation.authentication.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhi.amplify_store.domain.usecase.RegisterUseCase
import com.abhi.amplify_store.domain.usecase.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
): ViewModel(){

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

    private val _effects = Channel<RegisterUiEffect>()
    val effects = _effects.receiveAsFlow()

    fun onEmailChanged(email: String) = _uiState.update { it.copy(email = email) }
    fun onPasswordChanged(password: String) = _uiState.update { it.copy(password = password) }
    fun onAgreementChanged(agreed: Boolean) = _uiState.update { it.copy(agreement = agreed) }

    fun onEvent(event: RegisterUiEvent){
        when(event){
            is RegisterUiEvent.OnRegisterClick -> {
                register()
            }

            is RegisterUiEvent.NavigateToLogin -> {

            }

            is RegisterUiEvent.ShowSnackBar -> {

            }
        }
    }

    fun register(){
        viewModelScope.launch {
            val currentState = _uiState.value
            _uiState.update { it.copy(isLoading = true) }
            try {
                when(val result = registerUseCase(currentState.email, currentState.password)){
                    is Result.Success -> {
                        _effects.send(RegisterUiEffect.NavigateToLogin)
                    }
                    is Result.Error -> {
                        _effects.send(RegisterUiEffect.ShowError(result.message))
                    }
                }
            }catch (e: Exception){
                _effects.send(RegisterUiEffect.ShowError(e.message ?: "User Registration Failed"))            }
        }
    }
}