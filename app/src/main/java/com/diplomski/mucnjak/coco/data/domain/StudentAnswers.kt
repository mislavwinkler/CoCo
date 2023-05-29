package com.diplomski.mucnjak.coco.data.domain

import com.diplomski.mucnjak.coco.data.ui.Answer
import com.diplomski.mucnjak.coco.data.ui.Question

data class StudentAnswers(
    val studentIndex: Int,
    val question: Question,
    val answers: MutableList<Answer> = mutableListOf()
)
