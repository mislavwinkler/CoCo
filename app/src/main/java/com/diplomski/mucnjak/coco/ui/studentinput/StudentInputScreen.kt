package com.diplomski.mucnjak.coco.ui.studentinput

import SAMSUNG_SM_X200
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diplomski.mucnjak.coco.data.ui.Student
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.ui.common.*
import com.diplomski.mucnjak.coco.ui.theme.LocalCustomColor
import com.diplomski.mucnjak.coco.ui.theme.Shapes

@Composable
fun StudentInputScreen(navigateToWelcome: () -> Unit) {
    Content(navigateToWelcome)
}

@Composable
private fun Content(
    navigateToWelcome: () -> Unit,
    viewModel: StudentInputViewModel = hiltViewModel()
) {
    viewModel.OnState { state ->
        when (state) {
            is StudentInputState.Input -> Input(
                numOfStudents = state.numOfStudents,
                students = state.students,
                viewModel::confirmStudent
            )
            else -> DoNothing
        }
    }
}

@Composable
private fun Input(
    numOfStudents: Int,
    students: List<Student>,
    confirmStudent: (name: String, index: Int) -> Unit,
) {
    SplitScreenContainer(numOfStudents) { index ->
        CustomKeyboardContainer {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 8.dp),
                backgroundColor = when (index) {
                    1 -> LocalCustomColor.current.colorStudent1
                    2 -> LocalCustomColor.current.colorStudent2
                    3 -> LocalCustomColor.current.colorStudent3
                    else -> LocalCustomColor.current.colorStudent4
                },
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.rotate(-90f),
                        text = "Co|Co",
                        style = MaterialTheme.typography.subtitle1
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 16.dp)
                            .wrapContentWidth(),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 8.dp),
                            text = "What is your name?"
                        )
                        Column(
                            modifier = (if (isKeyboardShown) Modifier.fillMaxSize() else Modifier)
                                .padding(top = 16.dp)
                                .align(Alignment.CenterHorizontally),
                            verticalArrangement = if (isKeyboardShown) Arrangement.SpaceBetween else Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            CustomKeyboardTextField(placeholder = "YOUR NAME")
                            CustomKeyboard { confirmStudent(inputText, index) }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, device = SAMSUNG_SM_X200)
@Composable
private fun Preview() {
    Content({})
}
