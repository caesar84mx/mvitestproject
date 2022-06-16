package com.caesar84mx.mvitestproject.ui.screens.home.viewmodel

import android.text.Html
import androidx.lifecycle.viewModelScope
import com.caesar84mx.mvitestproject.abstracts.BaseViewModel
import com.caesar84mx.mvitestproject.data.api.ChuckyApi
import com.caesar84mx.mvitestproject.ui.screens.home.viewmodel.HomeEvent.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val api: ChuckyApi,
    reducer: HomeReducer
) : BaseViewModel<HomeState, HomeEvent>(reducer) {
    private val genericErrorMessage =
        "This fact has been roundhouse kicked from the past by Chuck Norris while fetching. Please, try to fetch a new one."

    init {
        viewModelScope.launch {
            getNewFact()
        }
    }

    fun onNextFactClick() {
        viewModelScope.launch {
            sendEvent(OnNewFactClick)
            getNewFact()
        }
    }

    fun onAlertDismiss() {
        sendEvent(OnAlertDismiss)
    }

    private suspend fun getNewFact() {
        val event: HomeEvent = try {
            val response = api.getRandomFact()
            if (response.type != "success") {
                ShowError(genericErrorMessage)
            } else {
                ShowFact(response.value?.fact?.normalized() ?: "Nothing here")
            }
        } catch (ex: Throwable) {
            ShowError(ex.message ?: genericErrorMessage)
        }

        sendEvent(event)
    }

    private fun String.normalized() = Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
}