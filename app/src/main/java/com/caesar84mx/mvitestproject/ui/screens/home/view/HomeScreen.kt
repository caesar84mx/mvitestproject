package com.caesar84mx.mvitestproject.ui.screens.home.view

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.caesar84mx.mvitestproject.R
import com.caesar84mx.mvitestproject.ui.components.AnimatedText
import com.caesar84mx.mvitestproject.ui.components.ErrorAlert
import com.caesar84mx.mvitestproject.ui.components.StatelyButton
import com.caesar84mx.mvitestproject.ui.screens.home.viewmodel.HomeState
import com.caesar84mx.mvitestproject.ui.screens.home.viewmodel.HomeViewModel
import com.caesar84mx.mvitestproject.ui.theme.MVITestProjectTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = getViewModel()
    val context = rememberCoroutineScope().coroutineContext
    val state = viewModel.state.collectAsState(
        context = context,
        initial = HomeState.initial()
    )

    Body(
        state = state.value,
        onNextFactClick = viewModel::onNextFactClick,
        onAlertDismiss = viewModel::onAlertDismiss
    )
}

@Composable
private fun Body(
    state: HomeState,
    onNextFactClick: () -> Unit,
    onAlertDismiss: () -> Unit
) {
    Surface {
        if (state.isError && state.message != null) {
            ErrorAlert(
                message = state.message,
                onDismiss = onAlertDismiss
            )
        }

        ConstraintLayout(
            modifier = Modifier
                .padding(
                    top = 20.dp,
                    bottom = 30.dp,
                    start = 20.dp,
                    end = 20.dp,
                )
                .fillMaxSize()
        ) {
            val (avatar, title, text, button) = createRefs()
            val alpha: Float by animateFloatAsState(
                targetValue = if (state.isLoading || state.isError) .7f else 1f,
                animationSpec = repeatable(1, TweenSpec(500))
            )

            Image(
                painter = painterResource(id = R.drawable.chucky),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = alpha,
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = 10.dp,
                            topEnd = 10.dp,
                        )
                    )
                    .constrainAs(avatar) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)

                        width = Dimension.fillToConstraints
                    }
            )

            Text(
                text = stringResource(id = R.string.fact_title_text),
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .constrainAs(title) {
                        top.linkTo(avatar.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)

                        width = Dimension.fillToConstraints
                    }
            )

            AnimatedText(
                animatedText = state.data ?: "\uD83D\uDE11",
                modifier = Modifier.constrainAs(text) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(title.bottom)

                    width = Dimension.fillToConstraints
                }
            )

            StatelyButton(
                label = stringResource(R.string.next_fact_button_label),
                showLoading = state.isLoading,
                onClick = onNextFactClick,
                modifier = Modifier.constrainAs(button) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)

                    width = Dimension.fillToConstraints
                }
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MVITestProjectTheme {
        Body(
            state = HomeState(
                isLoading = false,
                isError = false,
                message = "This fact has been roundhouse kicked from the past by Chuck Norris while fetching. Please, try to fetch a new one.",
                data = "Chuck Norris doesn't read books. He stares them down until he gets the information he wants"
            ),
            onNextFactClick = {},
            onAlertDismiss = {}
        )
    }
}