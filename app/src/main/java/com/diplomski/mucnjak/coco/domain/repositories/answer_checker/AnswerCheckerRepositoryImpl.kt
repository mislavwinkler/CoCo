package com.diplomski.mucnjak.coco.domain.repositories.answer_checker

import com.diplomski.mucnjak.coco.data.ui.Answer
import com.diplomski.mucnjak.coco.data.ui.Question
import com.diplomski.mucnjak.coco.domain.repositories.students.StudentRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.roundToInt

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

    override fun getStudentAccuracy(studentIndex: Int): Int {
        val studentAnswers = studentRepository.getAllStudentsAnswers()
            .first { (index, _, _) -> index == studentIndex }
        val numberOfCorrectAnswers = studentAnswers.question.answers.size
        val numOfStudentCorrectAnswers =
            studentAnswers.answers.count { answer -> studentAnswers.question.answers.contains(answer) }
        val numOfStudentIncorrectAnswers = studentAnswers.answers.size - numOfStudentCorrectAnswers
        return ((numOfStudentCorrectAnswers.toFloat() / (numberOfCorrectAnswers + numOfStudentIncorrectAnswers)) * 100).roundToInt()
    }

    override fun getStudentCorrectAnswers(studentIndex: Int): List<Answer> {
        val studentAnswers = studentRepository.getAllStudentsAnswers()
            .first { (index, _, _) -> index == studentIndex }
        return studentAnswers.answers.filter { answer ->
            studentAnswers.question.answers.contains(
                answer
            )
        }.map { it }
    }

    override fun getStudentIncorrectAnswers(studentIndex: Int): List<Answer> {
        val studentAnswers = studentRepository.getAllStudentsAnswers()
            .first { (index, _, _) -> index == studentIndex }
        return studentAnswers.answers.filter { answer ->
            !studentAnswers.question.answers.contains(
                answer
            )
        }.map { it }
    }

    override fun getStudentQuestionCorrectAnswers(studentIndex: Int): List<Answer> {
        return studentRepository.getAllStudentsAnswers()
            .first { (index, _, _) -> index == studentIndex }.question.answers.map { it }
    }

    override fun isAnswerCorrect(studentIndex: Int, answer: Answer): Boolean =
        studentRepository.getAllStudentsAnswers()
            .first { it.studentIndex == studentIndex }.question.answers.contains(answer)
}
