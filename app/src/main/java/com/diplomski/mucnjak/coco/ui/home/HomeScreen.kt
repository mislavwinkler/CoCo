package com.diplomski.mucnjak.coco.ui.home

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
import com.diplomski.mucnjak.coco.ui.old.splash.HomeViewModel
import com.diplomski.mucnjak.coco.ui.theme.LocalCustomColor

@Composable
fun HomeScreen(navigateToSplitScreen: () -> Unit) {
    Content(navigateToSplitScreen = navigateToSplitScreen)
}

@Composable
private fun Content(
    navigateToSplitScreen: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    viewModel.OnState { state ->
        HandleState(
            state = state,
            loadActivity = viewModel::loadActivity,
            clearConfiguration = viewModel::clearActivity,
            start = navigateToSplitScreen,
        )
    }
}

@Composable
private fun HandleState(
    state: HomeState,
    loadActivity: () -> Unit,
    clearConfiguration: () -> Unit,
    start: () -> Unit,
) {
    when (state) {
        HomeState.Initial -> DoNothing
        HomeState.Failed -> FailedState(loadActivity)
        HomeState.Loading -> LoadingState()
        is HomeState.Loaded -> LoadedState(state, clearConfiguration, start)
    }
}

@Composable
private fun FailedState(
    loadActivity: () -> Unit,
) {
    HomeContent {
        Button(
            modifier = Modifier
                .padding(top = 10.667.dp)
                .height(37.333.dp)
                .fillMaxWidth(0.3f),
            onClick = { loadActivity() }
        ) {
            Text(text = "Initialize!")
        }
    }
}

@Composable
private fun LoadingState() {
    HomeContent {
        Button(
            modifier = Modifier
                .padding(top = 10.667.dp)
                .height(37.333.dp)
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
    state: HomeState.Loaded,
    clearConfiguration: () -> Unit,
    start: () -> Unit,
) {
    HomeContent {
        Row(
            modifier = Modifier
                .padding(top = 10.667.dp)
                .fillMaxWidth(0.4f),
        ) {
            Button(
                modifier = Modifier
                    .height(37.333.dp)
                    .fillMaxWidth(0.75f),
                onClick = { clearConfiguration() }
            ) {
                Text(text = "Clear configuration!")
            }
            Button(
                modifier = Modifier
                    .padding(start = 5.333.dp)
                    .height(37.333.dp)
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
private fun HomeContent(
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(color = LocalCustomColor.current.neutralBackground)
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
        state = HomeState.Initial,
        loadActivity = { DoNothing },
        clearConfiguration = { DoNothing },
        start = { DoNothing }
    )
}

@Preview(showSystemUi = true, device = SAMSUNG_SM_X200)
@Composable
private fun PreviewLoading() {
    HandleState(
        state = HomeState.Loading,
        loadActivity = { DoNothing },
        clearConfiguration = { DoNothing },
        start = { DoNothing }
    )
}

@Preview(showSystemUi = true, device = SAMSUNG_SM_X200)
@Composable
private fun PreviewLoaded() {
    HandleState(
        state = HomeState.Loaded(2, "Topic title", "Subtopic title"),
        loadActivity = { DoNothing },
        clearConfiguration = { DoNothing },
        start = { DoNothing }
    )
}
