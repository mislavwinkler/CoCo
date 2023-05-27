package com.diplomski.mucnjak.coco.ui.split_screen

import SAMSUNG_SM_X200
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.ui.common.*
import com.diplomski.mucnjak.coco.ui.theme.StudentCoCoTheme

@Composable
fun SplitScreen() {
    Content()
}

@Composable
private fun Content(
    viewModel: SplitViewModel = hiltViewModel()
) {
    viewModel.OnState { state ->
        when (state) {
            is SplitState.Initial -> SplitScreens(
                numOfStudents = state.numOfStudents,
                rotations = state.rotations
            )
            else -> DoNothing
        }
    }
}

val LocalStudentIndex = compositionLocalOf { 0 }

@Composable
private fun SplitScreens(
    numOfStudents: Int,
    rotations: List<Int>,
) {
    SplitScreenContainer(numOfStudents, rotations) { index ->
        CompositionLocalProvider(LocalStudentIndex provides index) {
            StudentCoCoTheme(studentIndex = index) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 5.333.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    IndividualNavigationContent()
                }
            }
        }
    }
}

@Preview(showSystemUi = true, device = SAMSUNG_SM_X200)
@Composable
private fun Preview() {
    Content()
}
