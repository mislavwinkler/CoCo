package com.diplomski.mucnjak.coco.data.ui

data class Answer(
    val value: String,
    val type: AnswerType,
    val incorrect: Boolean = false,
    val notSelected: Boolean = false,
)
