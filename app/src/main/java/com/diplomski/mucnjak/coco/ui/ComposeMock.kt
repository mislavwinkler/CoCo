package com.diplomski.mucnjak.coco.ui

import com.diplomski.mucnjak.coco.data.ui.Answer
import com.diplomski.mucnjak.coco.data.ui.AnswerType
import com.diplomski.mucnjak.coco.data.ui.Question

object ComposeMock {

    const val SAMSUNG_SM_X200: String =
        "spec:width=800dp,height=1280dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"

    const val STUDENT_NAME: String = "Student name"
    const val TIME: String = "10:00"
    const val TIME_MINUTES: String = "10"
    const val QUESTION_TEXT: String = "Question text"
    const val TOPIC: String = "Topic"
    const val SUBTOPIC: String = "Subtopic"
    const val TOPIC_SUBTOPIC: String = "Topic | Subtopic"
    const val ACTIVITY_DESCRIPTION: String = "In next X minutes:\nSelect correct answers."

    const val LOREM_IPSUM: String = "LOREM IPSUM"
    const val IMAGE_URL: String =
        "https://images.unsplash.com/photo-1685399124857-ab2bc1053311?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1365&q=80"

    fun buildAnswersList(): List<Answer> = buildList {
        for (i in 1..5) {
            add(
                Answer(
                    value = "Random answer",
                    type = AnswerType.TEXT
                )
            )
            add(
                Answer(
                    value = "Ans",
                    type = AnswerType.TEXT,
                )
            )
        }
    }.shuffled()

    fun buildAnswersWithIncorrectList(): List<Answer> = buildList {
        for (i in 1..5) {
            add(
                Answer(
                    value = "Random answer",
                    type = AnswerType.TEXT
                )
            )
            add(
                Answer(
                    value = "Ans",
                    type = AnswerType.TEXT,
                )
            )
        }
    }.shuffled()

    fun buildQuestion(): Question = Question(
        questionText = QUESTION_TEXT,
        answers = buildAnswersList()
    )
}
