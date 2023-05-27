package com.diplomski.mucnjak.coco.ui.home

sealed class HomeState {
    object Initial : HomeState()

    object Failed : HomeState()

    object Loading : HomeState()

    data class Loaded(
        val numOfStudents: Int,
        val topic: String,
        val subTopic: String,
    ) : HomeState()
}
