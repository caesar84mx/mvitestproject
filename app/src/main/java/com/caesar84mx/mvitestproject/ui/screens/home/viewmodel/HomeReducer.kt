package com.caesar84mx.mvitestproject.ui.screens.home.viewmodel

import com.caesar84mx.mvitestproject.abstracts.Reducer
import com.caesar84mx.mvitestproject.ui.screens.home.viewmodel.HomeEvent.OnNewFactClick
import com.caesar84mx.mvitestproject.ui.screens.home.viewmodel.HomeEvent.ShowFact
import com.caesar84mx.mvitestproject.ui.screens.home.viewmodel.HomeEvent.ShowError
import com.caesar84mx.mvitestproject.ui.screens.home.viewmodel.HomeEvent.OnAlertDismiss

class HomeReducer(initial: HomeState): Reducer<HomeState, HomeEvent>(initial) {
    override fun reduce(oldState: HomeState, event: HomeEvent) {
        when(event) {
            OnNewFactClick -> {
                setState(oldState.copy(isLoading = true))
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
            is OnAlertDismiss -> {
                setState(
                    oldState.copy(
                        isLoading = false,
                        isError = false,
                        message = null
                    )
                )
            }
        }
    }
}