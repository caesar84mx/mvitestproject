package com.caesar84mx.mvitestproject.abstracts

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class Reducer<S : UiState, E : UiEvent>(initialVal: S) {
    private val _state: MutableStateFlow<S> = MutableStateFlow(initialVal)
    val state: StateFlow<S>
        get() = _state

    fun sendEvent(event: E) {
        reduce(_state.value, event)
    }

    fun setState(state: S) {
        _state.tryEmit(state)
    }

    abstract fun reduce(oldState: S, event: E)
}

interface UiEvent
interface UiState