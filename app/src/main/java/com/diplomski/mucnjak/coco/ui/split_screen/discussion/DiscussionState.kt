package com.diplomski.mucnjak.coco.ui.split_screen.discussion

import com.diplomski.mucnjak.coco.data.ui.Answer

sealed class DiscussionState {
    object Initial : DiscussionState()

    data class Discussion(
        val studentName: String,
        val question: String,
        val answers: List<Answer>,
        val selectedAnswers: List<Answer>,
        val time: String,
    ) : DiscussionState()
}
