package com.diplomski.mucnjak.coco.ui.solutions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.diplomski.mucnjak.coco.R
import com.diplomski.mucnjak.coco.data.ui.Question
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.ui.ComposeMock
import com.diplomski.mucnjak.coco.ui.components.*
import com.diplomski.mucnjak.coco.ui.components.answer_container.AnswerContainer
import com.diplomski.mucnjak.coco.ui.theme.*

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
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = Dimens.x3, bottom = Dimens.x3),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.padding(start = Dimens.x2)
                ) {
                    Text(
                        text = stringResource(R.string.solutions),
                        style = MaterialTheme.typography.h3,
                        color = MaterialTheme.colors.primary,
                    )
                    Text(
                        modifier = Modifier.padding(top = Dimens.x2),
                        text = title,
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.primary,
                    )
                }
                ReverseBranch()
            }
            FlowRow(
                modifier = Modifier
                    .padding(Dimens.x1)
                    .weight(1f)
                    .verticalScroll(state = rememberScrollState()),
                maxItemsInEachRow = 2
            ) {
                questions.forEach { (questionText, answers) ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(fraction = 0.5f)
                            .padding(horizontal = Dimens.x1, vertical = Dimens.x1)
                            .height(Dimens.solutionsCardHeight),
                        shape = cardShape(),
                        elevation = Dimens.zero,
                        backgroundColor = LocalCustomColor.current.neutralBackground,
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(all = Dimens.x2),
                        ) {
                            Text(
                                text = stringResource(
                                    id = R.string.solutions_for_question,
                                    questionText
                                ),
                                style = MaterialTheme.typography.body1,
                                color = MaterialTheme.colors.primary
                            )
                            AnswerContainer(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = Dimens.x1),
                                answers = answers,
                                onAnswerClick = { DoNothing },
                                customButtonColor = LocalCustomColor.current.neutralBackground
                            )
                        }
                    }
                }
            }
            CocoButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = Dimens.x1),
                text = stringResource(R.string.finish),
                onClick = confirmSolutions
            )
        }
    }
}

@Composable
private fun Congratulations(
    confirmCongratulations: () -> Unit,
    returnToSolutions: () -> Unit,
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
                TextButton(
                    modifier = Modifier.padding(start = Dimens.x3),
                    text = stringResource(R.string.return_to_solutions),
                    onClick = returnToSolutions,
                )
                ReverseBranch()
            }
            BirdOnBranch()
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.congratulations),
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.secondary
            )
            Text(
                text = stringResource(id = R.string.congratulations_description),
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.secondary,
                textAlign = TextAlign.Center
            )
            CocoButton(
                modifier = Modifier.padding(top = Dimens.x8),
                text = stringResource(id = R.string.finish),
                onClick = confirmCongratulations
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
private fun PreviewSolutions() {
    CoCoTheme {
        Solutions(title = ComposeMock.TOPIC_SUBTOPIC,
            questions = buildList {
                repeat(4) {
                    add(ComposeMock.buildQuestion())
                }
            },
            confirmSolutions = { DoNothing })
    }
}

@Composable
@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
private fun PreviewCongratulations() {
    CoCoTheme {
        Congratulations(
            returnToSolutions = { DoNothing },
            confirmCongratulations = { DoNothing }
        )
    }
}
