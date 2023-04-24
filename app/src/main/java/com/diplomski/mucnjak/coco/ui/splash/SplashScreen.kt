package com.diplomski.mucnjak.coco.ui.splash

import SAMSUNG_SM_X200
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.ui.common.OnState
import com.diplomski.mucnjak.coco.ui.theme.LocalCustomColor

@Composable
fun SplashScreen(navigateToNameInput: () -> Unit) {
    Content(navigateToNameInput)
}

@Composable
private fun Content(
    navigateToNameInput: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    viewModel.OnState { state ->
        HandleState(
            state = state,
            initialize = viewModel::initializeActivity,
            clearConfiguration = viewModel::clearActivity,
            start = navigateToNameInput,
        )
    }
}

@Composable
private fun HandleState(
    state: SplashState,
    initialize: () -> Unit,
    clearConfiguration: () -> Unit,
    start: () -> Unit,
) {
    when (state) {
        SplashState.Initial -> InitialState(initialize)
        SplashState.Loading -> LoadingState()
        is SplashState.Loaded -> LoadedState(state, clearConfiguration, start)
    }
}

@Composable
private fun InitialState(
    initialize: () -> Unit,
) {
    SplashContent {
        Button(
            modifier = Modifier
                .padding(top = 16.dp)
                .height(56.dp)
                .fillMaxWidth(0.3f),
            onClick = { initialize() }
        ) {
            Text(text = "Initialize!")
        }
    }
}

@Composable
private fun LoadingState() {
    SplashContent {
        Button(
            modifier = Modifier
                .padding(top = 16.dp)
                .height(56.dp)
                .fillMaxWidth(0.3f),
            onClick = { DoNothing },
            enabled = false
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun LoadedState(
    state: SplashState.Loaded,
    clearConfiguration: () -> Unit,
    start: () -> Unit,
) {
    SplashContent {
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(0.4f),
        ) {
            Button(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(0.75f),
                onClick = { clearConfiguration() }
            ) {
                Text(text = "Clear configuration!")
            }
            Button(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .height(56.dp)
                    .fillMaxWidth(),
                onClick = { start() }
            ) {
                Text(text = "Start!")
            }
        }
        Column {
            Text(
                text = state.topic,
                style = MaterialTheme.typography.h2,
                color = Color.White,
            )
            Text(
                text = state.subTopic,
                style = MaterialTheme.typography.h3,
                color = Color.White,
            )
            Text(
                text = "This device has configuration for ${state.numOfStudents} students."
            )
        }
    }
}

@Composable
private fun SplashContent(
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(color = LocalCustomColor.current.colorStudent1)
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Co|Co",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h1,
                color = Color.White,
            )
            content()
        }
    }
}

@Preview(showSystemUi = true, device = SAMSUNG_SM_X200)
@Composable
private fun PreviewInitial() {
    HandleState(
        state = SplashState.Initial,
        initialize = { DoNothing },
        clearConfiguration = { DoNothing },
        start = { DoNothing }
    )
}

@Preview(showSystemUi = true, device = SAMSUNG_SM_X200)
@Composable
private fun PreviewLoading() {
    HandleState(
        state = SplashState.Loading,
        initialize = { DoNothing },
        clearConfiguration = { DoNothing },
        start = { DoNothing }
    )
}

@Preview(showSystemUi = true, device = SAMSUNG_SM_X200)
@Composable
private fun PreviewLoaded() {
    HandleState(
        state = SplashState.Loaded(2, "Topic title", "Subtopic title"),
        initialize = { DoNothing },
        clearConfiguration = { DoNothing },
        start = { DoNothing }
    )
}
