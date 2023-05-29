package com.diplomski.mucnjak.coco.data.domain

import com.diplomski.mucnjak.coco.data.ui.AnswerType

data class AnswersDomainModel(
    val answers: List<String>,
    val answerType: AnswerType
)
