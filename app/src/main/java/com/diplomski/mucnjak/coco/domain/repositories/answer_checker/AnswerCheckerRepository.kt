package com.diplomski.mucnjak.coco.domain.repositories.answer_checker

import com.diplomski.mucnjak.coco.data.ui.Answer

interface AnswerCheckerRepository {
    /**
     * @return True if answers are correct
     */
    fun checkAnswers(): Boolean

    fun addIncorrectAnswerInfo(answers: List<Answer>): List<Answer>

    fun getStudentAccuracy(studentIndex: Int): Int
    fun getStudentCorrectAnswers(studentIndex: Int): List<Answer>
    fun getStudentIncorrectAnswers(studentIndex: Int): List<Answer>
    fun getStudentQuestionCorrectAnswers(studentIndex: Int): List<Answer>
    fun isAnswerCorrect(studentIndex: Int, answer: Answer): Boolean
}