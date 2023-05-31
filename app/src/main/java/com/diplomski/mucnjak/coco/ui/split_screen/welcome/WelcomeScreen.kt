package com.diplomski.mucnjak.coco.ui.split_screen.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.diplomski.mucnjak.coco.R
import com.diplomski.mucnjak.coco.extensions.addWithSize
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.ui.ComposeMock
import com.diplomski.mucnjak.coco.ui.components.*
import com.diplomski.mucnjak.coco.ui.split_screen.LocalStudentIndex
import com.diplomski.mucnjak.coco.ui.theme.CoCoTheme
import com.diplomski.mucnjak.coco.ui.theme.Dimens
import com.diplomski.mucnjak.coco.ui.theme.LocalSpecialTypography

@Composable
fun WelcomeScreen(navigateToSolving: () -> Unit) {
    Content(navigateToSolving)
}

@Composable
private fun Content(
    navigateToSolving: () -> Unit,
    viewModel: WelcomeViewModel = hiltViewModel()
) {
    val index = LocalStudentIndex.current
    LaunchedEffect(key1 = Unit) {
        viewModel.init(index)
    }
    viewModel.OnState { state ->
        when (state) {
            WelcomeState.Initial -> DoNothing
            is WelcomeState.ActivityPreview -> ActivityPreview(
                rotateScreen = { viewModel.rotateScreen(studentIndex = index) },
                confirmActivityPreview = { viewModel.onConfirmActivityPreview(studentIndex = index) },
                state = state
            )
        }
    }
    viewModel.OnNavigationEvent {
        if (it == NavigateToSolving) {
            navigateToSolving()
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ActivityPreview(
    rotateScreen: () -> Unit,
    confirmActivityPreview: () -> Unit,
    state: WelcomeState.ActivityPreview
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .addWithSize(
                    addOnLarge = { fillMaxWidth(0.5f) },
                    addOnSmall = { fillMaxWidth(0.7f) }
                )
                .align(Alignment.Center)
        ) {
            Text(
                text = state.studentName,
                style = LocalSpecialTypography.current.WelcomeHello,
                color = MaterialTheme.colors.primary,
            )
            FlowRow(
                modifier = Modifier.padding(top = Dimens.x4)
            ) {
                Text(
                    text = state.topic,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.primary,
                )
                Text(
                    text = stringResource(id = R.string.subtopic_separator, state.subtopic),
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.primary,
                )
            }
            Text(
                modifier = Modifier.padding(top = Dimens.x2),
                text = state.description,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary,
            )
            RotateConfirmContainer(
                modifier = Modifier
                    .addWithSize(
                        addOnLarge = { padding(top = Dimens.x8) },
                        addOnSmall = { padding(top = Dimens.x2) }
                    ),
                onRotate = rotateScreen,
                onConfirm = confirmActivityPreview,
                isConfirmed = state.isConfirmed,
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
private fun PreviewActivityPreview() {
    CoCoTheme {
        ActivityPreview(
            rotateScreen = { DoNothing },
            confirmActivityPreview = { DoNothing },
            state = WelcomeState.ActivityPreview(
                studentName = ComposeMock.STUDENT_NAME,
                topic = ComposeMock.TOPIC,
                subtopic = "| ${ComposeMock.SUBTOPIC}",
                description = ComposeMock.ACTIVITY_DESCRIPTION
            )
        )
    }
}
