package com.diplomski.mucnjak.coco.ui.split_screen.solving

sealed class SolvingNavigationEvent {
    object NavigateToIncorrectSolution : SolvingNavigationEvent()

    object NavigateToFinishNote : SolvingNavigationEvent()
}