package com.diplomski.mucnjak.coco.ui.split_screen.setup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.ui.components.ConfirmButton
import com.diplomski.mucnjak.coco.ui.components.OnNavigationEvent
import com.diplomski.mucnjak.coco.ui.components.OnState
import com.diplomski.mucnjak.coco.ui.components.RotateButton
import com.diplomski.mucnjak.coco.ui.split_screen.LocalStudentIndex
import com.diplomski.mucnjak.coco.ui.theme.LocalSpecialTypography
import com.diplomski.mucnjak.coco.ui.theme.Dimens

@Composable
fun SetupScreen(navigateToWelcome: () -> Unit) {
    Content(navigateToWelcome)
}

@Composable
private fun Content(
    navigateToWelcome: () -> Unit,
    viewModel: SetupViewModel = hiltViewModel()
) {
    val index = LocalStudentIndex.current
    LaunchedEffect(key1 = Unit) {
        viewModel.init(studentIndex = index)
    }
    viewModel.OnState { state ->
        when (state) {
            SetupState.Initial -> DoNothing
            is SetupState.SetupRotation -> SetupRotation(
                rotateScreen = { viewModel.rotateScreen(studentIndex = index) },
                confirmSetup = { viewModel.confirmSetup(studentIndex = index) },
                studentName = state.studentName
            )
        }
    }
    viewModel.OnNavigationEvent {
        if (it == SetupNavigationEvent.NavigateToWelcomeScreen) {
            navigateToWelcome()
        }
    }
}

@Composable
private fun SetupRotation(
    rotateScreen: () -> Unit,
    confirmSetup: () -> Unit,
    studentName: String
) {
    Column {
        Text(
            text = "Hi,",
            style = LocalSpecialTypography.current.WelcomeHello,
        )
        Text(
            modifier = Modifier.padding(bottom = Dimens.x3_75),
            text = "$studentName!",
            style = LocalSpecialTypography.current.WelcomeName,
        )
        Text(
            text = "Rotate the screen if it doesn't suit you\n" +
                    "and confirm by clicking on the check mark.",
            style = MaterialTheme.typography.body1,
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            RotateButton { rotateScreen() }
            ConfirmButton { confirmSetup() }
        }
    }
}