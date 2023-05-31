package com.diplomski.mucnjak.coco.ui.split_screen.setup

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
import com.diplomski.mucnjak.coco.extensions.addWithSize
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.ui.ComposeMock
import com.diplomski.mucnjak.coco.ui.components.OnNavigationEvent
import com.diplomski.mucnjak.coco.ui.components.OnState
import com.diplomski.mucnjak.coco.ui.components.RotateConfirmContainer
import com.diplomski.mucnjak.coco.ui.split_screen.LocalStudentIndex
import com.diplomski.mucnjak.coco.ui.theme.CoCoTheme
import com.diplomski.mucnjak.coco.ui.theme.Dimens
import com.diplomski.mucnjak.coco.ui.theme.LocalSpecialTypography

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
                state = state,
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
    state: SetupState.SetupRotation,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .addWithSize(
                    addOnLarge = { fillMaxWidth(0.5f) },
                    addOnSmall = { fillMaxWidth(0.7f) }
                )
                .align(Alignment.Center)
        ) {
            Text(
                text = stringResource(R.string.hi),
                style = LocalSpecialTypography.current.WelcomeHello,
                color = MaterialTheme.colors.primary,
            )
            Text(
                text = stringResource(id = R.string.exclamation_name, state.studentName),
                style = LocalSpecialTypography.current.WelcomeName,
                color = MaterialTheme.colors.primary,
            )
            Text(
                modifier = Modifier.padding(bottom = Dimens.x2),
                text = stringResource(id = R.string.rotate_setup_description),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary,
            )
            RotateConfirmContainer(
                modifier = Modifier
                    .addWithSize(
                        addOnLarge = { padding(top = Dimens.x8) },
                        addOnSmall = { padding(top = Dimens.x2) }
                    ),
                onRotate = rotateScreen,
                onConfirm = confirmSetup,
                isConfirmed = state.isConfirmed,
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
private fun PreviewSetupRotation() {
    CoCoTheme {
        SetupRotation(
            rotateScreen = { DoNothing },
            confirmSetup = { DoNothing },
            state = SetupState.SetupRotation(
                studentName = ComposeMock.STUDENT_NAME,
            )
        )
    }
}
