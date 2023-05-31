package com.diplomski.mucnjak.coco.ui.split_screen.retry_note

sealed class RetryNoteState {
    data class Initial(val isConfirmed: Boolean = false) : RetryNoteState()
}
