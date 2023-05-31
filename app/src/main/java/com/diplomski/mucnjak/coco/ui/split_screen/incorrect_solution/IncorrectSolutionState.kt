package com.diplomski.mucnjak.coco.ui.split_screen.incorrect_solution

sealed class IncorrectSolutionState {
    object Initial : IncorrectSolutionState()

    data class Note(
        val timeToDiscuss: String,
        val isConfirmed: Boolean = false,
    ) : IncorrectSolutionState()
}
