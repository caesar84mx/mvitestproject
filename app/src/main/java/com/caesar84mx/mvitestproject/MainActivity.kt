package com.caesar84mx.mvitestproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.caesar84mx.mvitestproject.ui.screens.home.view.HomeScreen
import com.caesar84mx.mvitestproject.ui.theme.MVITestProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVITestProjectTheme {
                HomeScreen()
            }
        }
    }
}
