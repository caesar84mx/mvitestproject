package com.caesar84mx.mvitestproject.di

import com.caesar84mx.mvitestproject.ui.screens.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { HomeViewModel(get(), get()) }
}