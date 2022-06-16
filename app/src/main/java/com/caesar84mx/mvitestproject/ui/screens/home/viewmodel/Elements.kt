package com.caesar84mx.mvitestproject.ui.screens.home.viewmodel

import androidx.compose.runtime.Immutable
import com.caesar84mx.mvitestproject.abstracts.UiEvent
import com.caesar84mx.mvitestproject.abstracts.UiState

@Immutable
sealed class HomeEvent: UiEvent {
    object OnNewFactClick: HomeEvent()
    object OnAlertDismiss: HomeEvent()
    data class ShowFact(val joke: String): HomeEvent()
    data class ShowError(val message: String): HomeEvent()
}

@Immutable
data class HomeState(
    val isLoading: Boolean,
    val data: String?,
    val isError: Boolean,
    val message: String?
): UiState {
    companion object {
        fun initial() = HomeState(
            isLoading = true,
            data = null,
            isError = false,
            message = null
        )
    }
}