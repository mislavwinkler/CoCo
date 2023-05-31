package com.diplomski.mucnjak.coco.ui.split_screen.welcome

sealed class WelcomeState {
    object Initial : WelcomeState()

    data class ActivityPreview(
        val studentName: String,
        val topic: String,
        val subtopic: String,
        val description: String,
        val isConfirmed: Boolean = false,
    ) : WelcomeState()
}
