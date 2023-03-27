package com.diplomski.mucnjak.coco.ui.studentinput

import SAMSUNG_SM_X200
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diplomski.mucnjak.coco.data.ui.Student
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.ui.common.*

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
            is StudentInputState.Input -> Input(numOfStudents = state.numOfStudents, students = state.students, viewModel::confirmStudent)
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
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                CustomKeyboardTextField()
                CustomKeyboard { confirmStudent(inputText, index) }
            }
        }
    }
}

@Preview(showSystemUi = true, device = SAMSUNG_SM_X200)
@Composable
private fun Preview() {
    Content({})
}
