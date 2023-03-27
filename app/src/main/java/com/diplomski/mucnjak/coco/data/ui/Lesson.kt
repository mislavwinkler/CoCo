package com.diplomski.mucnjak.coco.data.ui

import com.diplomski.mucnjak.coco.extensions.empty

data class Lesson(
    val topicName: String = String.empty,
)

data class SubTopic(
    val name: String = String.empty
)

data class Task(
    val description: String = String.empty,
    val answers: List<Answer> = emptyList()
)

data class Answer(
    val description: String = String.empty
)
