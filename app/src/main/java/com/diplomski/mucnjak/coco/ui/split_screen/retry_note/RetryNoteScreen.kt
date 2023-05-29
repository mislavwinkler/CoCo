package com.diplomski.mucnjak.coco.ui.split_screen.retry_note

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.diplomski.mucnjak.coco.ui.common.ConfirmButton
import com.diplomski.mucnjak.coco.ui.common.OnNavigationEvent
import com.diplomski.mucnjak.coco.ui.common.OnState
import com.diplomski.mucnjak.coco.ui.common.RotateButton
import com.diplomski.mucnjak.coco.ui.split_screen.LocalStudentIndex

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
            RetryNoteState.Initial -> RetryNote(
                rotateScreen = { viewModel.rotateScreen(studentIndex = index) },
                confirmNext = { viewModel.confirmStudentNextStep(studentIndex = index) },
            )
        }
    }
    viewModel.OnNavigationEvent {
        if (it == RetryNoteNavigationEvent.NavigateToSolving) {
            navigateToSolving()
        }
    }
}

@Composable
private fun RetryNote(
    rotateScreen: () -> Unit,
    confirmNext: () -> Unit,
) {
    Column {
        Text(
            text = "Try again!",
            style = MaterialTheme.typography.h3,
        )
        Text(
            text = "Now that you have discussed, try to correct you answers",
            style = MaterialTheme.typography.body1,
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            RotateButton { rotateScreen() }
            ConfirmButton { confirmNext() }
        }
    }
}