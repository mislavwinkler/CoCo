package com.diplomski.mucnjak.coco.ui.split_screen.discussion

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.diplomski.mucnjak.coco.data.ui.Answer
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.ui.common.ConfirmButton
import com.diplomski.mucnjak.coco.ui.common.OnNavigationEvent
import com.diplomski.mucnjak.coco.ui.common.OnState
import com.diplomski.mucnjak.coco.ui.common.RotateButton
import com.diplomski.mucnjak.coco.ui.common.answer_container.AnswerContainer
import com.diplomski.mucnjak.coco.ui.split_screen.LocalStudentIndex
import com.diplomski.mucnjak.coco.ui.split_screen.solving.AnswerContainers

@Composable
fun DiscussionScreen(navigateToRetry: () -> Unit) {
    Content(navigateToRetry)
}

@Composable
private fun Content(
    navigateToRetry: () -> Unit,
    viewModel: DiscussionViewModel = hiltViewModel()
) {
    val index = LocalStudentIndex.current
    LaunchedEffect(key1 = Unit) {
        viewModel.init(index)
    }
    viewModel.OnState { state ->
        when (state) {
            DiscussionState.Initial -> DoNothing
            is DiscussionState.Discussion -> Discussion(
                studentName = state.studentName,
                time = state.time,
                question = state.question,
                answers = state.answers,
                selectedAnswers = state.selectedAnswers,
                rotateScreen = { viewModel.rotateScreen(index) },
                confirmDiscussionEnd = { viewModel.confirmStudentNextStep(index) }
            )
        }
    }

    viewModel.OnNavigationEvent { navigationEvent ->
        if (navigationEvent == DiscussionNavigationEvent.NavigateToRetry) {
            navigateToRetry()
        }
    }
}

@Composable
private fun Discussion(
    studentName: String,
    time: String,
    question: String,
    answers: List<Answer>,
    selectedAnswers: List<Answer>,
    rotateScreen: () -> Unit,
    confirmDiscussionEnd: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(start = 26.667.dp, top = 26.667.dp, end = 20.dp, bottom = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = studentName,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary,
            )
            Text(
                text = time,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.primary,
            )
        }
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = question,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary
        )
        AnswerContainers(
            modifier = Modifier.padding(top = 16.dp),
        ) { modifier ->
            AnswerContainer(
                modifier = modifier,
                answers = answers,
                onAnswerClick = { DoNothing }
            )
            AnswerContainer(
                modifier = modifier,
                answers = selectedAnswers,
                onAnswerClick = { DoNothing }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            RotateButton { rotateScreen() }
            ConfirmButton { confirmDiscussionEnd() }
        }
    }
}

