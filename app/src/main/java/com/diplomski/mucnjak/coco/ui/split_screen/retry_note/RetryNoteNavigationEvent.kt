package com.diplomski.mucnjak.coco.ui.split_screen.retry_note

sealed class RetryNoteNavigationEvent {
    object NavigateToSolving : RetryNoteNavigationEvent()
}
