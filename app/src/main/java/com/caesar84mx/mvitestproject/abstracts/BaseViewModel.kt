package com.caesar84mx.mvitestproject.abstracts

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

abstract class BaseViewModel<S: UiState, in E: UiEvent>(private val reducer: Reducer<S, E>): ViewModel() {
    val state: Flow<S>
        get() = reducer.state

    protected fun sendEvent(event: E) {
        reducer.sendEvent(event)
    }
}