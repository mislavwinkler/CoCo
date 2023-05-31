package com.diplomski.mucnjak.coco.ui.split_screen.incorrect_solution

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.diplomski.mucnjak.coco.R
import com.diplomski.mucnjak.coco.extensions.addWithSize
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.ui.ComposeMock
import com.diplomski.mucnjak.coco.ui.components.OnNavigationEvent
import com.diplomski.mucnjak.coco.ui.components.OnState
import com.diplomski.mucnjak.coco.ui.components.RotateConfirmContainer
import com.diplomski.mucnjak.coco.ui.split_screen.LocalStudentIndex
import com.diplomski.mucnjak.coco.ui.theme.CoCoTheme
import com.diplomski.mucnjak.coco.ui.theme.Dimens

@Composable
fun IncorrectSolutionScreen(navigateToDiscussion: () -> Unit) {
    Content(navigateToDiscussion = navigateToDiscussion)
}

@Composable
private fun Content(
    navigateToDiscussion: () -> Unit,
    viewModel: IncorrectSolutionViewModel = hiltViewModel()
) {
    val index = LocalStudentIndex.current
    viewModel.OnState { state ->
        when (state) {
            IncorrectSolutionState.Initial -> DoNothing
            is IncorrectSolutionState.Note -> IncorrectSolutionNote(
                rotateScreen = { viewModel.rotateScreen(index) },
                confirmNext = { viewModel.confirmStudentNextStep(index) },
                state = state,
            )
        }
    }
    viewModel.OnNavigationEvent {
        if (it == IncorrectSolutionNavigationEvent.NavigateToDiscussion) {
            navigateToDiscussion()
        }
    }
}

@Composable
private fun IncorrectSolutionNote(
    rotateScreen: () -> Unit,
    confirmNext: () -> Unit,
    state: IncorrectSolutionState.Note,
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
                text = stringResource(R.string.something_is_wrong),
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.primary,
            )
            Text(
                text = stringResource(id = R.string.discussion_description, state.timeToDiscuss),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary,
            )
            Text(
                text = stringResource(R.string.discussion_additional_description),
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
                onConfirm = confirmNext,
                isConfirmed = state.isConfirmed,
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
private fun PreviewIncorrectSolutionNote() {
    CoCoTheme {
        IncorrectSolutionNote(
            rotateScreen = { DoNothing },
            confirmNext = { DoNothing },
            state = IncorrectSolutionState.Note(timeToDiscuss = ComposeMock.TIME_MINUTES)
        )
    }
}
