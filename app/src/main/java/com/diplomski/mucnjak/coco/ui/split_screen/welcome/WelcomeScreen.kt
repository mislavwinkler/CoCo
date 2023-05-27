package com.diplomski.mucnjak.coco.ui.split_screen.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.ui.common.ConfirmButton
import com.diplomski.mucnjak.coco.ui.common.OnNavigationEvent
import com.diplomski.mucnjak.coco.ui.common.OnState
import com.diplomski.mucnjak.coco.ui.common.RotateButton
import com.diplomski.mucnjak.coco.ui.split_screen.LocalStudentIndex
import com.diplomski.mucnjak.coco.ui.theme.LocalSpecialTypography
import com.diplomski.mucnjak.coco.ui.theme.Spacing

@Composable
fun WelcomeScreen(navigateToSolving: () -> Unit) {
    Content(navigateToSolving)
}

@Composable
private fun Content(
    navigateToSolving: () -> Unit,
    viewModel: WelcomeViewModel = hiltViewModel()
) {
    val index = LocalStudentIndex.current
    LaunchedEffect(key1 = Unit) {
        viewModel.init(index)
    }
    viewModel.OnState { state ->
        when (state) {
            WelcomeState.Initial -> DoNothing
            is WelcomeState.ActivityPreview -> ActivityPreview(
                rotateScreen = { viewModel.rotateScreen(studentIndex = index) },
                confirmActivityPreview = { viewModel.onConfirmActivityPreview(studentIndex = index) },
                state = state
            )
        }
    }
    viewModel.OnNavigationEvent {
        if (it == NavigateToSolving) {
            navigateToSolving()
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ActivityPreview(
    rotateScreen: () -> Unit,
    confirmActivityPreview: () -> Unit,
    state: WelcomeState.ActivityPreview
) {
    Column {
        Text(
            text = state.studentName,
            style = LocalSpecialTypography.current.WelcomeHello,
        )
        FlowRow {
            Text(
                text = state.topic,
                style = MaterialTheme.typography.subtitle1,
            )
            Text(
                modifier = Modifier.padding(bottom = Spacing.x3_75),
                text = "| ${state.subtopic}",
                style = MaterialTheme.typography.subtitle1,
            )
        }
        Text(
            text = state.description,
            style = MaterialTheme.typography.body1,
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            RotateButton { rotateScreen() }
            ConfirmButton { confirmActivityPreview() }
        }
    }
}
