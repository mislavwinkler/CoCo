package com.diplomski.mucnjak.coco.analytics

import android.os.Bundle
import com.diplomski.mucnjak.coco.data.ui.Answer
import com.diplomski.mucnjak.coco.data.ui.AnswerType
import com.diplomski.mucnjak.coco.domain.repositories.active_activity.ActiveActivityRepository
import com.diplomski.mucnjak.coco.domain.repositories.answer_checker.AnswerCheckerRepository
import com.diplomski.mucnjak.coco.domain.repositories.iteration.IterationRepository
import com.diplomski.mucnjak.coco.domain.repositories.students.StudentRepository
import com.diplomski.mucnjak.coco.domain.repositories.uuid.UuidRepository
import com.google.firebase.Timestamp
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAnalytics @Inject constructor(
    private val activeActivityRepository: ActiveActivityRepository,
    private val uuidRepository: UuidRepository,
    private val firebaseAnalytics: com.google.firebase.analytics.FirebaseAnalytics,
    private val studentRepository: StudentRepository,
    private val answerCheckerRepository: AnswerCheckerRepository,
    private val iterationRepository: IterationRepository
) : Analytics {


    override fun sendActivityAnalytics() {
        val activeActivity =
            activeActivityRepository.getLocalActiveActivity() ?: throw NullPointerException()
        firebaseAnalytics.setUserId(uuidRepository.getUuid().toString())
        firebaseAnalytics.logEvent("activity_initialization", Bundle().apply {
            putString("activity_id", activeActivity.id)
            putString("topic", activeActivity.topic)
            putString("subtopic", activeActivity.subTopic)
            putInt("number_of_students", activeActivity.activeNumOfStudents)
            putString(
                "correction_times", activeActivity.correctionTimes.joinToString(
                    prefix = "\"", separator = "\", \"", postfix = "\""
                )
            )
            putString(
                "discussion_times", activeActivity.discussionTimes.joinToString(
                    prefix = "\"", separator = "\", \"", postfix = "\""
                )
            )
            putInt("solving_time", activeActivity.solvingTime)
            putInt("group_index", activeActivity.groupIndex)
            putString("time", Timestamp.now().toString())
        })
    }


    override fun sendStudents() {
        firebaseAnalytics.logEvent("student", Bundle().apply {
            putString(
                "name",
                studentRepository.getAllStudents()
                    .joinToString(prefix = "\"", separator = "\", \"", postfix = "\"") { it.name }.take(100)
            )
            putString("time", Timestamp.now().toString())
        })
    }


    override fun sendStudentRotation(studentIndex: Int, screen: String) {
        firebaseAnalytics.logEvent("student_rotation", Bundle().apply {
            putString("name", studentRepository.getStudent(studentIndex)?.name)
            putString("screen", screen)
            putInt(
                "rotation", studentRepository.getStudent(studentIndex)?.rotation ?: -1
            )
            putString("time", Timestamp.now().toString())
        })
    }


    override fun sendResults() {
        studentRepository.getAllStudents().forEachIndexed { studentIndex, _ ->
            firebaseAnalytics.logEvent("student_results", Bundle().apply {
                putString("name", studentRepository.getStudent(studentIndex)?.name)
                putInt("iteration", iterationRepository.getCurrentIteration())
                putInt("accuracy", answerCheckerRepository.getStudentAccuracy(studentIndex))
                putString(
                    "correct_answers",
                    answerCheckerRepository.getStudentQuestionCorrectAnswers(studentIndex)
                        .joinToString(
                            prefix = "\"", separator = "\", \"", postfix = "\""
                        ) { (value, type, _) ->
                            if (type == AnswerType.IMAGE) value.takeLast(
                                10
                            ) else value.take(100)
                        }.take(100)
                )
                putString(
                    "student_correct_answers",
                    answerCheckerRepository.getStudentCorrectAnswers(studentIndex).joinToString(
                        prefix = "\"", separator = "\", \"", postfix = "\""
                    ) { (value, type, _) ->
                        if (type == AnswerType.IMAGE) value.takeLast(
                            10
                        ) else value.take(100)
                    }.take(100)
                )
                putString(
                    "student_incorrect_answers",
                    answerCheckerRepository.getStudentIncorrectAnswers(studentIndex).joinToString(
                        prefix = "\"", separator = "\", \"", postfix = "\""
                    ) { (value, type, _) ->
                        if (type == AnswerType.IMAGE) value.takeLast(
                            10
                        ) else value.take(100)
                    }.take(100)
                )
                putString("time", Timestamp.now().toString())
            })
        }
    }

    // Probably not needed
    override fun sendScreenStart(screen: String, studentIndex: Int?) {
        firebaseAnalytics.logEvent("screen_started", Bundle().apply {
            putString("screen_name", screen)
            studentIndex?.let {
                putString(
                    "name", studentRepository.getStudent(studentIndex)?.name
                )
                putString("time", Timestamp.now().toString())
            }
        })
    }


    override fun sendAnswerSelected(studentIndex: Int, answer: Answer) {
        firebaseAnalytics.logEvent("answer_selected", Bundle().apply {
            putString("name", studentRepository.getStudent(studentIndex)?.name)
            putString("answer", answer.value.run {
                if (answer.type == AnswerType.IMAGE) takeLast(100) else take(100)
            })
            putBoolean(
                "is_action_correct",
                answerCheckerRepository.isAnswerCorrect(studentIndex, answer)
            )
            putString("time", Timestamp.now().toString())
        })
    }


    override fun sendAnswerUnselected(studentIndex: Int, answer: Answer) {
        firebaseAnalytics.logEvent("answer_unselected", Bundle().apply {
            putString("name", studentRepository.getStudent(studentIndex)?.name)
            putString("answer", answer.value.run {
                if (answer.type == AnswerType.IMAGE) takeLast(100) else take(100)
            })
            putBoolean(
                "is_action_correct",
                !answerCheckerRepository.isAnswerCorrect(studentIndex, answer)
            )
            putString("time", Timestamp.now().toString())
        })
    }


    override fun sendStudentReady(studentIndex: Int, screen: String) {
        firebaseAnalytics.logEvent("student_ready", Bundle().apply {
            putString("name", studentRepository.getStudent(studentIndex)?.name)
            putString("screen_name", screen)
            putString("time", Timestamp.now().toString())
        })
    }


    override fun sendStudentUnready(studentIndex: Int, screen: String) {
        firebaseAnalytics.logEvent("student_unready", Bundle().apply {
            putString("name", studentRepository.getStudent(studentIndex)?.name)
            putString("screen_name", screen)
            putString("time", Timestamp.now().toString())
        })
    }
}