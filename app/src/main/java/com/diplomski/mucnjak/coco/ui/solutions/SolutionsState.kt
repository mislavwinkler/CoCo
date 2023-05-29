package com.diplomski.mucnjak.coco.ui.solutions

import com.diplomski.mucnjak.coco.data.ui.Question

sealed class SolutionsState {
    object Initial : SolutionsState()

    data class Solutions(val title: String, val questions: List<Question>) : SolutionsState()

    object Congratulations : SolutionsState()
}