package com.diplomski.mucnjak.coco.ui.split_screen.retry_note

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
fun RetryNoteScreen(navigateToSolving: () -> Unit) {
    Content(navigateToSolving)
}

@Composable
private fun Content(
    navigateToSolving: () -> Unit,
    viewModel: RetryNoteViewModel = hiltViewModel(),
) {
    val index = LocalStudentIndex.current
    viewModel.OnState { state ->
        when (state) {
            is RetryNoteState.Initial -> RetryNote(
                state = state,
                rotateScreen = { viewModel.rotateScreen(studentIndex = index) },
                confirmNext = { viewModel.confirmStudentNextStep(studentIndex = index) },
            )
        }
    }
    viewModel.OnNavigationEvent { navigationEvent ->
        if (navigationEvent == RetryNoteNavigationEvent.NavigateToSolving) {
            navigateToSolving()
        }
    }
}

@Composable
private fun RetryNote(
    state: RetryNoteState.Initial,
    rotateScreen: () -> Unit,
    confirmNext: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .addWithSize(
                    addOnLarge = { fillMaxWidth(0.5f) },
                    addOnSmall = { fillMaxWidth(0.7f) }
                )
                .align(Alignment.Center)
        ) {
            Text(
                text = stringResource(R.string.try_again),
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.primary,
            )
            Text(
                modifier = Modifier.padding(top = Dimens.x5),
                text = stringResource(R.string.retry_description),
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
private fun PreviewRetryNote() {
    CoCoTheme {
        RetryNote(
            state = RetryNoteState.Initial(),
            rotateScreen = { DoNothing },
            confirmNext = { DoNothing })
    }
}
