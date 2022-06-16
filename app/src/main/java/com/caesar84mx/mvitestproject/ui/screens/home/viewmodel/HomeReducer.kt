package com.caesar84mx.mvitestproject.ui.screens.home.viewmodel

import com.caesar84mx.mvitestproject.abstracts.Reducer
import com.caesar84mx.mvitestproject.ui.screens.home.viewmodel.HomeEvent.*

class HomeReducer(initial: HomeState): Reducer<HomeState, HomeEvent>(initial) {
    override fun reduce(oldState: HomeState, event: HomeEvent) {
        when(event) {
            OnNewFactClick -> {
                setState(oldState.copy(isLoading = true))
            }

            OnAlertDismiss -> {
                setState(
                    oldState.copy(
                        isLoading = false,
                        isError = false,
                        message = null
                    )
                )
            }

            is ShowFact -> {
                setState(
                    oldState.copy(
                        isLoading = false,
                        data = event.joke,
                        isError = false,
                        message = null
                    )
                )
            }

            is ShowError -> {
                setState(
                    oldState.copy(
                        isLoading = false,
                        isError = true,
                        message = event.message
                    )
                )
            }
        }
    }
}