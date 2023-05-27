package com.diplomski.mucnjak.coco.ui.split_screen.incorrect_solution

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.ui.common.ConfirmButton
import com.diplomski.mucnjak.coco.ui.common.OnNavigationEvent
import com.diplomski.mucnjak.coco.ui.common.OnState
import com.diplomski.mucnjak.coco.ui.common.RotateButton
import com.diplomski.mucnjak.coco.ui.split_screen.LocalStudentIndex

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
                discussionTime = state.timeToDiscuss,
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
    discussionTime: String,
) {
    Column {
        Text(
            text = "Something is wrong!",
            style = MaterialTheme.typography.h3,
        )
        Text(
            text = "You have $discussionTime minutes to discuss your solutions and then try again.",
            style = MaterialTheme.typography.body1,
        )
        Text(
            text = "In this section you cannot change your answers.",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight(400),
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            RotateButton { rotateScreen() }
            ConfirmButton { confirmNext() }
        }
    }
}