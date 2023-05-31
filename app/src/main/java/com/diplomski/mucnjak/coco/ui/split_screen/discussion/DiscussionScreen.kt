package com.diplomski.mucnjak.coco.ui.split_screen.discussion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.diplomski.mucnjak.coco.extensions.addWithSize
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.ui.ComposeMock
import com.diplomski.mucnjak.coco.ui.components.*
import com.diplomski.mucnjak.coco.ui.components.answer_container.AnswerContainer
import com.diplomski.mucnjak.coco.ui.split_screen.LocalStudentIndex
import com.diplomski.mucnjak.coco.ui.theme.CoCoTheme
import com.diplomski.mucnjak.coco.ui.theme.Dimens

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
                state = state,
                rotateScreen = { viewModel.rotateScreen(index) },
                confirmNext = { viewModel.confirmStudentNextStep(index) }
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
    state: DiscussionState.Discussion,
    rotateScreen: () -> Unit,
    confirmNext: () -> Unit,
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
                onAnswerClick = { DoNothing }
            )
            AnswerContainer(
                modifier = modifier,
                answers = state.selectedAnswers,
                onAnswerClick = { DoNothing }
            )
        }
        RotateConfirmContainer(
            modifier = Modifier
                .addWithSize(
                    addOnLarge = { padding(top = Dimens.x1) },
                    addOnSmall = { padding(top = Dimens.x0_5) }
                ),
            onRotate = rotateScreen,
            onConfirm = confirmNext,
            isConfirmed = state.isConfirmed,
        )
    }
}

@Composable
@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
private fun PreviewDiscussion() {
    CoCoTheme {
        Discussion(
            state = DiscussionState.Discussion(
                studentName = ComposeMock.STUDENT_NAME,
                time = ComposeMock.TIME,
                question = ComposeMock.QUESTION_TEXT,
                answers = ComposeMock.buildAnswersWithIncorrectList(),
                selectedAnswers = ComposeMock.buildAnswersWithIncorrectList(),
            ),
            rotateScreen = { DoNothing },
            confirmNext = { DoNothing }
        )
    }
}

