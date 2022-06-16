package com.caesar84mx.mvitestproject.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StatelyButton(
    modifier: Modifier = Modifier,
    label: String,
    showLoading: Boolean,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        if (showLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = onClick,
            ) {
                Text(
                    text = label,
                    modifier = Modifier.padding(
                        vertical = 10.dp,
                        horizontal = 20.dp
                    )
                )
            }
        }
    }
}