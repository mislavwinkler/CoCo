package com.diplomski.mucnjak.coco.ui.split_screen.studentinput

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.diplomski.mucnjak.coco.R
import com.diplomski.mucnjak.coco.ui.ComposeMock
import com.diplomski.mucnjak.coco.ui.components.*
import com.diplomski.mucnjak.coco.ui.split_screen.LocalStudentIndex
import com.diplomski.mucnjak.coco.ui.theme.Dimens

@Composable
fun StudentInputScreen(navigateToSetup: () -> Unit) {
    Content(navigateToSetup)
}

@Composable
private fun Content(
    navigateToSetup: () -> Unit,
    viewModel: StudentInputViewModel = hiltViewModel()
) {
    viewModel.OnState { state ->
        when (state) {
            is StudentInputState.Input -> Input(
                confirmStudent = viewModel::confirmStudent,
                isConfirmed = state.isConfirmed,
            )
        }
    }
    viewModel.OnNavigationEvent {
        if (it == StudentInputNavigationEvent.NavigateToSetup) {
            navigateToSetup()
        }
    }
}

@Composable
private fun Input(
    confirmStudent: (name: String, index: Int) -> Unit,
    isConfirmed: Boolean,
) {
    val studentIndex = LocalStudentIndex.current
    CustomKeyboardContainer {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = Dimens.x1)
                .wrapContentWidth(),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier.padding(start = Dimens.x1),
                text = stringResource(R.string.what_is_your_name)
            )
            Column(
                modifier = (if (isKeyboardShown) Modifier.fillMaxSize() else Modifier)
                    .padding(
                        top = Dimens.x1
                    )
                    .align(Alignment.CenterHorizontally),
                verticalArrangement = if (isKeyboardShown) Arrangement.SpaceBetween else Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomKeyboardTextField(
                        placeholder = stringResource(R.string.your_name)
                    )
                    ToggleConfirmButton(
                        modifier = Modifier.padding(start = Dimens.x1),
                        isConfirmed = isConfirmed,
                    ) {
                        confirmStudent(inputText, studentIndex)
                    }
                }
                CustomKeyboard {
                    confirmStudent(inputText, studentIndex)
                }
            }
        }
    }
}

@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
@Composable
private fun Preview() {
    Input({ _, _ -> }, false)
}
