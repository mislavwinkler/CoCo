package com.diplomski.mucnjak.coco.ui.solutions

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.diplomski.mucnjak.coco.data.ui.Question
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.ui.common.OnNavigationEvent
import com.diplomski.mucnjak.coco.ui.common.OnState
import com.diplomski.mucnjak.coco.ui.common.answer_container.AnswerContainer

@Composable
fun SolutionsScreen(navigateToStart: () -> Unit) {
    Content(navigateToStart)
}

@Composable
private fun Content(
    navigateToStart: () -> Unit,
    viewModel: SolutionsViewModel = hiltViewModel()
) {
    viewModel.OnState { state ->
        when (state) {
            is SolutionsState.Solutions -> Solutions(
                title = state.title,
                questions = state.questions,
                confirmSolutions = viewModel::confirmSolutions
            )
            is SolutionsState.Congratulations -> Congratulations(
                confirmCongratulations = viewModel::confirmCongratulations,
                returnToSolutions = viewModel::returnToSolutions
            )
            else -> DoNothing
        }
    }

    viewModel.OnNavigationEvent {
        if (it == SolutionsNavigationEvent.NavigateToStart) {
            navigateToStart()
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Solutions(
    title: String,
    questions: List<Question>,
    confirmSolutions: () -> Unit,
) {
    Column {
        Text(
            text = "Solutions",
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.primary,
        )
        Text(
            text = title,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.primary,
        )
        FlowRow(maxItemsInEachRow = 2) {
            questions.forEach { question ->
                Column(
                    modifier = Modifier.fillMaxWidth(fraction = 0.5f),
                ) {
                    Text(
                        text = "Solutions for:\n${question.questionText}",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.primary
                    )
                    AnswerContainer(
                        answers = question.answers,
                        onAnswerClick = { DoNothing })
                }
            }
        }
        Button(onClick = confirmSolutions) {
            Text(
                text = "Finish",
                style = MaterialTheme.typography.button,
                color = MaterialTheme.colors.onSurface,
            )
        }
    }
}

@Composable
private fun Congratulations(
    confirmCongratulations: () -> Unit,
    returnToSolutions: () -> Unit,
) {
    Column {
        Button(onClick = returnToSolutions) {
            Text(
                text = "Return to solutions",
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.secondary
            )
        }
        Text(
            text = "Congratulations!",
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.secondary
        )
        Text(
            text = "You handled this great, keep it up!\n" +
                    "The exercise is over, call the teacher.!",
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.secondary,
            textAlign = TextAlign.Center
        )
        Button(onClick = confirmCongratulations) {
            Text(
                text = "Finish",
                style = MaterialTheme.typography.button,
                color = MaterialTheme.colors.onSurface,
            )
        }
    }
}
