package com.diplomski.mucnjak.coco.domain.repositories.question

import com.diplomski.mucnjak.coco.data.ui.Question
import com.diplomski.mucnjak.coco.domain.mapper.question.QuestionsMappers
import com.diplomski.mucnjak.coco.domain.repositories.active_activity.ActiveActivityRepository
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionsRepositoryImpl @Inject constructor(
    private val activeActivityRepository: ActiveActivityRepository,
    private val questionsMappers: QuestionsMappers.QuestionMapper,
) : QuestionsRepository {

    private val availableQuestions: MutableList<Question> = mutableListOf()

    private val studentQuestion: MutableMap<Int, Question> = mutableMapOf()

    private val mutex = Mutex()

    override suspend fun getAvailableQuestion(studentIndex: Int): Question = studentQuestion[studentIndex]
        ?: mutex.withLock {
            if (availableQuestions.isEmpty()) {
                availableQuestions.addAll(
                    activeActivityRepository.getActiveActivity()
                        .let { questionsMappers.mapToUiModel(it) })
            }

            studentQuestion[studentIndex] = availableQuestions.first()

            return@withLock availableQuestions.removeFirst<Question>()
        }

    override suspend fun releaseQuestion(studentIndex: Int) {
        if (studentQuestion.containsKey(studentIndex)) {
            mutex.withLock {
                studentQuestion.remove(studentIndex)?.let { availableQuestions.add(it) }
            }
        }
    }

    override fun clearRepository() {
        availableQuestions.clear()
        studentQuestion.clear()
    }

    override fun getAllActiveQuestions(): List<Question> = studentQuestion.values.toList()
}
