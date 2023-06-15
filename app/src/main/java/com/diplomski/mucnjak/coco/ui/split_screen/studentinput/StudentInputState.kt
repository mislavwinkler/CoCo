package com.diplomski.mucnjak.coco.ui.split_screen.studentinput

sealed class StudentInputState {
    data class Input(val isConfirmed: Boolean = false) : StudentInputState()
}
