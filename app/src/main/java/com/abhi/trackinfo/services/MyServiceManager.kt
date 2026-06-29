package com.abhi.trackinfo.services

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyServiceManager {

    enum class State {
        NOT_STARTED,
        STARTED,
        STOPPED
    }

    enum class ForeState {
        NOT_STARTED,
        STARTED,
        STOPPED
    }

    // Regular Service Access Start

    private val _state = MutableStateFlow(State.NOT_STARTED)

    val state: StateFlow<State> = _state.asStateFlow()

    fun updateState(state: State){
        _state.value = state
    }

    // end


    // Foreground Service

    var stateFore = MutableStateFlow(ForeState.NOT_STARTED)



}
