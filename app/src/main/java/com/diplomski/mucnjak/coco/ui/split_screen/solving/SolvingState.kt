package com.diplomski.mucnjak.coco.ui.split_screen.solving

import com.diplomski.mucnjak.coco.data.ui.Answer

sealed class SolvingState {
    object Initial : SolvingState()

    data class Solving(
        val studentName: String,
        val question: String,
        val answers: List<Answer>,
        val selectedAnswers: List<Answer>,
        val time: String,
    ) : SolvingState()

    data class Congratulations(val studentName: String,val time: String,) : SolvingState()

    object Finished : SolvingState()
}
