package com.diplomski.mucnjak.coco.ui.split_screen.solving

import SAMSUNG_SM_X200
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.diplomski.mucnjak.coco.R
import com.diplomski.mucnjak.coco.data.ui.Answer
import com.diplomski.mucnjak.coco.data.ui.AnswerType
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.ui.common.*
import com.diplomski.mucnjak.coco.ui.common.answer_container.AnswerContainer
import com.diplomski.mucnjak.coco.ui.split_screen.LocalStudentIndex
import com.diplomski.mucnjak.coco.ui.split_screen.incorrect_solution.IncorrectSolutionState
import com.diplomski.mucnjak.coco.ui.theme.CoCoTheme
import com.diplomski.mucnjak.coco.ui.theme.LocalCustomColor
import kotlin.random.Random

@Composable
fun SolvingScreen(
    navigateToIncorrectSolution: () -> Unit,
    navigateToResults: () -> Unit
) {
    Content(navigateToIncorrectSolution)
}

@Composable
fun Content(
    navigateToIncorrectSolution: () -> Unit,
    viewModel: SolvingViewModel = hiltViewModel()
) {
    val index = LocalStudentIndex.current
    LaunchedEffect(key1 = Unit) {
        viewModel.init(index)
    }
    viewModel.OnState { state ->
        when (state) {
            is SolvingState.Solving -> Solving(
                studentName = state.studentName,
                time = state.time,
                question = state.question,
                answers = state.answers,
                selectedAnswers = state.selectedAnswers,
                onAnswerSelected = { viewModel.selectAnswer(it) },
                onAnswerDeselected = { viewModel.selectAnswer(it) },
                rotateScreen = { viewModel.rotateScreen(index) },
                confirmTaskSolved = viewModel::confirmTaskSolved
            )
            is SolvingState.Congratulations -> Congratulations(
                studentName = state.studentName,
                time = state.time,
                rotateScreen = { viewModel.rotateScreen(index) },
                returnToSolving = viewModel::returnToSolving
            )
            else -> DoNothing
        }
    }
    viewModel.OnNavigationEvent { navigationEvent ->
        when (navigationEvent) {
            AfterSolvingNavigationEvent.NavigateToIncorrectSolution -> navigateToIncorrectSolution()
        }
    }
}

@Composable
fun Solving(
    studentName: String,
    time: String,
    question: String,
    answers: List<Answer>,
    selectedAnswers: List<Answer>,
    onAnswerSelected: (Answer) -> Unit,
    onAnswerDeselected: (Answer) -> Unit,
    rotateScreen: () -> Unit,
    confirmTaskSolved: () -> Unit,
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
                onAnswerClick = onAnswerSelected
            )
            AnswerContainer(
                modifier = modifier,
                answers = selectedAnswers,
                onAnswerClick = onAnswerDeselected
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            RotateButton { rotateScreen() }
            ConfirmButton { confirmTaskSolved() }
        }
    }
}

@Composable
fun ColumnScope.AnswerContainers(
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit
) {
    val currentSize = LocalSize.current
    if (currentSize.width > currentSize.height) {
        Row(
            modifier = modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(26.667.dp)
        ) {
            content(
                Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )
        }
    } else {
        Column(
            modifier = modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(26.667.dp)
        ) {
            content(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}

@Composable
private fun Congratulations(
    studentName: String,
    time: String,
    rotateScreen: () -> Unit,
    returnToSolving: () -> Unit,
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
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            text = "Congratulations!",
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.primary,
        )
        Text(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            text = "Wait for others to finish",
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.primary,
        )

        Button(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            onClick = { returnToSolving() },
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_rotate),
                    contentDescription = null
                )
                Text(
                    text = "Go back to solving",
                    style = MaterialTheme.typography.body1,
                    color = LocalCustomColor.current.hyperlink,
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            RotateButton { rotateScreen() }
        }
    }
}

@Preview(device = SAMSUNG_SM_X200)
@Composable
private fun PreviewSolving() {
    CoCoTheme {
        Solving(
            studentName = "Student name",
            time = "10:00",
            question = "This is the question, is it now?",
            answers = buildList {
                for (i in 1..5) {
                    add(
                        Answer(
                            "Random answer",
                            type = AnswerType.TEXT
                        )
                    )
                    add(
                        Answer(
                            "Ans",
                            type = AnswerType.TEXT,
                            incorrect = Random.nextBoolean()
                        )
                    )
                }
            }.shuffled(),
            selectedAnswers = buildList {
                for (i in 1..5) {
                    add(
                        Answer(
                            "Random answer",
                            type = AnswerType.TEXT
                        )
                    )
                    add(
                        Answer(
                            "Ans",
                            type = AnswerType.TEXT,
                            incorrect = Random.nextBoolean()
                        )
                    )
                }
            }.shuffled(),
            onAnswerSelected = {},
            onAnswerDeselected = {},
            rotateScreen = {},
            confirmTaskSolved = {}
        )
    }
}

@Preview(device = SAMSUNG_SM_X200)
@Composable
private fun PreviewCongratulations() {
    CoCoTheme {
        Congratulations(
            studentName = "Student name",
            time = "10:00",
            rotateScreen = {},
            returnToSolving = {})
    }
}
