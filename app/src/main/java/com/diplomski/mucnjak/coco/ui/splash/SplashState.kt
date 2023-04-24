package com.diplomski.mucnjak.coco.ui.splash

sealed class SplashState {
    object Initial : SplashState()

    object Loading : SplashState()

    data class Loaded(
        val numOfStudents: Int,
        val topic: String,
        val subTopic: String,
    ) : SplashState()
}
