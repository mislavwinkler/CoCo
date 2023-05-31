package com.diplomski.mucnjak.coco.ui.split_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.ui.ComposeMock
import com.diplomski.mucnjak.coco.ui.components.OnNavigationEvent
import com.diplomski.mucnjak.coco.ui.components.OnState
import com.diplomski.mucnjak.coco.ui.components.roundedShadow
import com.diplomski.mucnjak.coco.ui.components.splitscreen.SplitScreenContainer
import com.diplomski.mucnjak.coco.ui.theme.CoCoTheme
import com.diplomski.mucnjak.coco.ui.theme.Dimens
import com.diplomski.mucnjak.coco.ui.theme.StudentCoCoTheme

@Composable
fun SplitScreen(navigateToSolutions: () -> Unit) {
    Content(navigateToSolutions)
}

@Composable
private fun Content(
    navigateToSolutions: () -> Unit,
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

    viewModel.OnNavigationEvent {
        if (it == SplitNavigationEvent.NavigateToSolutions) {
            navigateToSolutions()
        }
    }
}

val LocalStudentIndex: ProvidableCompositionLocal<Int> = compositionLocalOf { 0 }
val LocalIsLarge: ProvidableCompositionLocal<Boolean> = compositionLocalOf { false }

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
                        .padding(all = Dimens.x0_75)
                        .roundedShadow(
                            color = MaterialTheme.colors.secondaryVariant,
                            cornerShape = RoundedCornerShape(
                                topStart = MaterialTheme.shapes.small.topStart,
                                topEnd = MaterialTheme.shapes.large.topEnd,
                                bottomEnd = MaterialTheme.shapes.small.topEnd,
                                bottomStart = MaterialTheme.shapes.large.bottomStart,
                            )
                        ),
                    elevation = Dimens.zero,
                    shape = RoundedCornerShape(
                        topStart = MaterialTheme.shapes.small.topStart,
                        topEnd = MaterialTheme.shapes.large.topEnd,
                        bottomEnd = MaterialTheme.shapes.small.topEnd,
                        bottomStart = MaterialTheme.shapes.large.bottomStart,
                    )
                ) {
                    IndividualNavigationContent()
                }
            }
        }
    }
}

@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
@Composable
private fun Preview() {
    CoCoTheme {
        Content({ DoNothing })
    }
}
