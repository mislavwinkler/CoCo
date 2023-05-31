package com.diplomski.mucnjak.coco.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.diplomski.mucnjak.coco.R
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.ui.ComposeMock
import com.diplomski.mucnjak.coco.ui.components.*
import com.diplomski.mucnjak.coco.ui.theme.CoCoTheme
import com.diplomski.mucnjak.coco.ui.theme.Dimens
import com.diplomski.mucnjak.coco.ui.theme.LocalCustomColor
import com.diplomski.mucnjak.coco.ui.theme.LocalSpecialTypography

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
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.oh_no),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.secondary,
            )
            Text(
                text = stringResource(id = R.string.config_something_went_wrong),
                textAlign = TextAlign.Center,
                style = LocalSpecialTypography.current.WelcomeHello,
                color = MaterialTheme.colors.secondary,
            )
            CocoButton(
                modifier = Modifier.padding(top = Dimens.x3, bottom = Dimens.x8),
                text = stringResource(R.string.fetch_again),
                onClick = loadActivity
            )
        }
    }
}

@Composable
private fun LoadingState() {
    LoadingScreen()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LoadedState(
    state: HomeState.Loaded,
    clearConfiguration: () -> Unit,
    start: () -> Unit,
) {
    Box {
        HomeContent {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.hello),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.secondary,
                )
                Text(
                    text = stringResource(R.string.welcome),
                    textAlign = TextAlign.Center,
                    style = LocalSpecialTypography.current.WelcomeHello,
                    color = MaterialTheme.colors.secondary,
                )
                StartButton(
                    modifier = Modifier.padding(top = Dimens.x3, bottom = Dimens.x8),
                    text = stringResource(R.string.start),
                    onClick = start
                )
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = Dimens.x3, bottom = Dimens.x3)
                .combinedClickable(
                    onClick = { DoNothing },
                    onLongClick = { clearConfiguration() }
                ),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = stringResource(R.string.current_configuration),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondary,
            )
            KeyValueText(
                key = stringResource(R.string.group_size),
                value = state.numOfStudents.toString()
            )
            KeyValueText(key = stringResource(R.string.topic), value = state.topic)
            KeyValueText(key = stringResource(R.string.subtopic), value = state.subTopic)
        }
    }
}

@Composable
private fun KeyValueText(key: String, value: String) {
    Text(
        text = buildAnnotatedString {
            append("$key ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(value)
            }
        },
        style = MaterialTheme.typography.caption,
        color = MaterialTheme.colors.secondary,
    )
}

@Composable
private fun HomeContent(
    isError: Boolean = false,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = Modifier
            .background(color = LocalCustomColor.current.neutralBackground)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .background(color = LocalCustomColor.current.neutralBackground)
                .fillMaxSize()
                .padding(top = Dimens.x3, bottom = Dimens.x3),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Logo(modifier = Modifier.padding(start = Dimens.x3))
                ReverseBranch()
            }
            if (isError) {
                SadBirdOnBranch()
            } else {
                BirdOnBranch()
            }
        }
        content()
    }
}

@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
@Composable
private fun PreviewLoading() {
    CoCoTheme {
        HandleState(
            state = HomeState.Loading,
            loadActivity = { DoNothing },
            clearConfiguration = { DoNothing },
            start = { DoNothing }
        )
    }
}

@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
@Composable
private fun PreviewFailed() {
    CoCoTheme {
        HandleState(
            state = HomeState.Failed,
            loadActivity = { DoNothing },
            clearConfiguration = { DoNothing },
            start = { DoNothing }
        )
    }
}

@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
@Composable
private fun PreviewLoaded() {
    CoCoTheme {
        HandleState(
            state = HomeState.Loaded(2, ComposeMock.TOPIC, ComposeMock.SUBTOPIC),
            loadActivity = { DoNothing },
            clearConfiguration = { DoNothing },
            start = { DoNothing }
        )
    }
}
