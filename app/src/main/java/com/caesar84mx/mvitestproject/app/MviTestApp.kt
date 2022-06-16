package com.caesar84mx.mvitestproject.app

import android.app.Application
import com.caesar84mx.mvitestproject.di.appmodule
import com.caesar84mx.mvitestproject.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MviTestApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MviTestApp)
            modules(appmodule, viewModelsModule)
        }
    }
}