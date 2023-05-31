package com.diplomski.mucnjak.coco.ui.split_screen.setup

sealed class SetupState {
    object Initial : SetupState()

    data class SetupRotation(
        val studentName: String,
        val isConfirmed: Boolean = false
    ) : SetupState()
}
