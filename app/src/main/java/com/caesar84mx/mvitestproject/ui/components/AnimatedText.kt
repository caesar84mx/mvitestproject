package com.caesar84mx.mvitestproject.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedText(
    animatedText: String,
    modifier: Modifier = Modifier
) {
    AnimatedContent(
        targetState = animatedText,
        transitionSpec = {
            fadeIn(
                animationSpec = tween(
                    durationMillis = 300,
                    delayMillis = 30
                )
            ) with fadeOut(
                animationSpec = tween(
                    durationMillis = 300,
                    delayMillis = 30
                )
            )
        },
        modifier = modifier
    ) { text ->
        Text(
            text = text,
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center
        )
    }
}