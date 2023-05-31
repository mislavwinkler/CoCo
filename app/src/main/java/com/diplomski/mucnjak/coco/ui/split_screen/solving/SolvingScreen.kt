package com.diplomski.mucnjak.coco.ui.split_screen.solving

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
import com.diplomski.mucnjak.coco.data.ui.Answer
import com.diplomski.mucnjak.coco.extensions.addWithSize
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.ui.ComposeMock
import com.diplomski.mucnjak.coco.ui.components.*
import com.diplomski.mucnjak.coco.ui.components.answer_container.AnswerContainer
import com.diplomski.mucnjak.coco.ui.split_screen.LocalStudentIndex
import com.diplomski.mucnjak.coco.ui.theme.CoCoTheme
import com.diplomski.mucnjak.coco.ui.theme.Dimens

@Composable
fun SolvingScreen(
    navigateToIncorrectSolution: () -> Unit,
    navigateToFinishNote: () -> Unit,
) {
    Content(
        navigateToIncorrectSolution = navigateToIncorrectSolution,
        navigateToFinishNote = navigateToFinishNote
    )
}

@Composable
private fun Content(
    navigateToIncorrectSolution: () -> Unit,
    navigateToFinishNote: () -> Unit,
    viewModel: SolvingViewModel = hiltViewModel()
) {
    val index = LocalStudentIndex.current
    LaunchedEffect(key1 = Unit) {
        viewModel.init(index)
    }
    viewModel.OnState { state ->
        when (state) {
            is SolvingState.Solving -> Solving(
                state = state,
                onAnswerSelected = { answer ->
                    viewModel.selectAnswer(
                        studentIndex = index,
                        answer = answer
                    )
                },
                onAnswerDeselected = { answer ->
                    viewModel.selectAnswer(
                        studentIndex = index,
                        answer = answer
                    )
                },
                rotateScreen = { viewModel.rotateScreen(index = index) },
                confirmTaskSolved = { viewModel.confirmTaskSolved(studentIndex = index) }
            )
            is SolvingState.Congratulations -> Congratulations(
                state = state,
                returnToSolving = { viewModel.returnToSolving(studentIndex = index) }
            )
            else -> DoNothing
        }
    }
    viewModel.OnNavigationEvent { navigationEvent ->
        when (navigationEvent) {
            SolvingNavigationEvent.NavigateToIncorrectSolution -> navigateToIncorrectSolution()
            SolvingNavigationEvent.NavigateToFinishNote -> navigateToFinishNote()
        }
    }
}

@Composable
private fun Solving(
    state: SolvingState.Solving,
    onAnswerSelected: (Answer) -> Unit,
    onAnswerDeselected: (Answer) -> Unit,
    rotateScreen: () -> Unit,
    confirmTaskSolved: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(all = Dimens.x2)
    ) {
        InteractionHeader(
            title = state.studentName,
            time = state.time,
        )
        Text(
            modifier = Modifier.padding(top = Dimens.x2),
            text = state.question,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary
        )
        AnswersContainer(
            modifier = Modifier.padding(top = Dimens.x2),
        ) { modifier ->
            AnswerContainer(
                modifier = modifier,
                answers = state.answers,
                onAnswerClick = onAnswerSelected
            )
            AnswerContainer(
                modifier = modifier,
                answers = state.selectedAnswers,
                onAnswerClick = onAnswerDeselected
            )
        }
        RotateConfirmContainer(
            modifier = Modifier
                .addWithSize(
                    addOnLarge = { padding(top = Dimens.x1) },
                    addOnSmall = { padding(top = Dimens.x0_5) }
                ),
            onRotate = rotateScreen,
            onConfirm = confirmTaskSolved,
        )
    }
}

@Composable
private fun Congratulations(
    state: SolvingState.Congratulations,
    returnToSolving: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.x2),
    ) {
        InteractionHeader(
            title = state.studentName,
            time = state.time,
        )
        Column(
            modifier = Modifier
                .addWithSize(
                    addOnLarge = { fillMaxSize(0.5f) },
                    addOnSmall = { fillMaxWidth(0.7f) }
                )
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.congratulations),
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.primary,
            )
            Text(
                modifier = Modifier.padding(top = Dimens.x5),
                text = stringResource(R.string.congratulations_wait_for_others),
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.primary,
            )
            TextButton(
                modifier = Modifier.padding(top = Dimens.x8),
                text = stringResource(R.string.go_back_to_solving),
                onClick = returnToSolving
            )
        }
    }
}

@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
@Composable
private fun PreviewSolving() {
    CoCoTheme {
        Solving(
            state = SolvingState.Solving(
                studentName = ComposeMock.STUDENT_NAME,
                time = ComposeMock.TIME,
                question = ComposeMock.QUESTION_TEXT,
                answers = ComposeMock.buildAnswersList(),
                selectedAnswers = ComposeMock.buildAnswersList()
            ),
            onAnswerSelected = { DoNothing },
            onAnswerDeselected = { DoNothing },
            rotateScreen = { DoNothing },
            confirmTaskSolved = { DoNothing }
        )
    }
}

@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
@Composable
private fun PreviewCongratulations() {
    CoCoTheme {
        Congratulations(
            state = SolvingState.Congratulations(
                studentName = ComposeMock.STUDENT_NAME,
                time = ComposeMock.TIME,
            ),
            returnToSolving = { DoNothing })
    }
}
