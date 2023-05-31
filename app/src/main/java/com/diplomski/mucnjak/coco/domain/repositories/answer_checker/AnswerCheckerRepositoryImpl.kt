package com.diplomski.mucnjak.coco.domain.repositories.answer_checker

import com.diplomski.mucnjak.coco.data.ui.Answer
import com.diplomski.mucnjak.coco.data.ui.Question
import com.diplomski.mucnjak.coco.domain.repositories.students.StudentRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnswerCheckerRepositoryImpl @Inject constructor(
    private val studentRepository: StudentRepository,
) : AnswerCheckerRepository {

    private val incorrectAnswers: MutableSet<Answer> = mutableSetOf()

    override fun checkAnswers(): Boolean {
        incorrectAnswers.clear()
        val selectedAnswers = studentRepository.getAllStudentsAnswers()
        selectedAnswers.forEach { (_, question, answers) ->
            if (!compareQuestionAndAnswers(question, answers)) {
                incorrectAnswers += (question.answers - answers.toSet()) + (answers - question.answers.toSet())
            }
        }
        return incorrectAnswers.isEmpty()
    }

    private fun compareQuestionAndAnswers(question: Question, answers: List<Answer>) =
        question.answers.size == answers.size && question.answers.containsAll(answers)

    override fun addIncorrectAnswerInfo(answers: List<Answer>): List<Answer> =
        answers.map { answer ->
            if (incorrectAnswers.contains(answer)) {
                answer.copy(incorrect = true)
            } else {
                answer
            }
        }
}
