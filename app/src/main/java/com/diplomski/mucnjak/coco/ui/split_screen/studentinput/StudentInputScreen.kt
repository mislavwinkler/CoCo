package com.diplomski.mucnjak.coco.ui.split_screen.studentinput

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.diplomski.mucnjak.coco.ui.ComposeMock
import com.diplomski.mucnjak.coco.ui.components.*
import com.diplomski.mucnjak.coco.ui.split_screen.LocalStudentIndex

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
            is StudentInputState.Input -> Input(viewModel::confirmStudent)
        }
    }
    viewModel.OnNavigationEvent {
        if (it == StudentInputNavigationEvent.NavigateToSetup) {
            navigateToSetup()
        }
    }
}

@Composable
private fun Input(confirmStudent: (name: String, index: Int) -> Unit) {
    val studentIndex = LocalStudentIndex.current
    CustomKeyboardContainer {
        Row(
            modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.rotate(-90f),
                text = "Co|Co",
                style = MaterialTheme.typography.subtitle1
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.667.dp)
                    .wrapContentWidth(),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    modifier = Modifier.padding(start = 5.333.dp), text = "What is your name?"
                )
                Column(
                    modifier = (if (isKeyboardShown) Modifier.fillMaxSize() else Modifier)
                        .padding(
                            top = 10.667.dp
                        )
                        .align(Alignment.CenterHorizontally),
                    verticalArrangement = if (isKeyboardShown) Arrangement.SpaceBetween else Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row {
                        CustomKeyboardTextField(
                            placeholder = "YOUR NAME"
                        )
                        ConfirmButton(modifier = Modifier.size(33.333.dp)) {
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
}

@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
@Composable
private fun Preview() {
    Input { _, _ -> }
}
