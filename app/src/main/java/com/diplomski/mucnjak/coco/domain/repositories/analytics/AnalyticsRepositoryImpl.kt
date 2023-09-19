package com.diplomski.mucnjak.coco.domain.repositories.analytics

import com.diplomski.mucnjak.coco.data.domain.ResultsDomainModel
import com.diplomski.mucnjak.coco.data.domain.StudentResultsDomainModel
import com.diplomski.mucnjak.coco.domain.interactor.post_analytics_results.PostAnalyticsResultsInteractor
import com.diplomski.mucnjak.coco.domain.mapper.analytics.AnalyticsMappers
import com.diplomski.mucnjak.coco.domain.repositories.active_activity.ActiveActivityRepository
import com.diplomski.mucnjak.coco.domain.repositories.answer_checker.AnswerCheckerRepository
import com.diplomski.mucnjak.coco.domain.repositories.clock.ClockRepository
import com.diplomski.mucnjak.coco.domain.repositories.iteration.IterationRepository
import com.diplomski.mucnjak.coco.domain.repositories.students.StudentRepository
import com.google.firebase.Timestamp
import javax.inject.Inject

class AnalyticsRepositoryImpl @Inject constructor(
    private val activeActivityRepository: ActiveActivityRepository,
    private val studentRepository: StudentRepository,
    private val answerCheckerRepository: AnswerCheckerRepository,
    private val clockRepository: ClockRepository,
    private val postAnalyticsResultsInteractor: PostAnalyticsResultsInteractor,
    private val analyticsNetworkMapper: AnalyticsMappers.AnalyticsNetworkMapper,
    private val iterationRepository: IterationRepository,
) : AnalyticsRepository {

    private lateinit var results: ResultsDomainModel

    private val studentResults
        get() = results.results

    private val discussionTimes
        get() = results.discussionTimes

    override fun init() {
        val activeActivity = activeActivityRepository.getLocalActiveActivity() ?: throw NullPointerException()
        results = ResultsDomainModel(
            activityId = activeActivity.id,
            date = Timestamp.now(),
            group = 0,
            subtopic = activeActivity.subTopic,
            topic = activeActivity.topic,
            discussionTimes = mutableListOf(),
            results = mutableListOf(),
        )
        studentRepository.getAllStudents().forEach { (name, position, _) ->
            studentResults.add(
                StudentResultsDomainModel(
                    studentIndex = position,
                    name = name,
                    accuracies = mutableListOf(),
                    initialResolutionTimes = mutableListOf(),
                    studentResult = mutableMapOf(),
                )
            )
        }
    }

    override fun calculateAndStoreAccuracies() {
        studentResults.forEach { (studentIndex, _, accuracies, _, result) ->
            val studentCorrectAnswers = answerCheckerRepository.getStudentCorrectAnswers(studentIndex)
            val studentIncorrectAnswers = answerCheckerRepository.getStudentIncorrectAnswers(studentIndex)
            val studentQuestionAnswers = answerCheckerRepository.getStudentQuestionCorrectAnswers(studentIndex)
            result["markedCorrect${iterationRepository.getCurrentIteration()}"] =
                studentCorrectAnswers.size
            result["markedIncorrect${iterationRepository.getCurrentIteration()}"] =
                (studentIncorrectAnswers - studentQuestionAnswers.toSet()).size
            result["unmarkedCorrect${iterationRepository.getCurrentIteration()}"] =
                (studentQuestionAnswers - studentCorrectAnswers.toSet()).size
            accuracies.add(answerCheckerRepository.getStudentAccuracy(studentIndex))
        }
    }

    override fun storeDiscussionTime() {
        discussionTimes.add(clockRepository.getElapsedTime())
    }

    override fun storeResolutionChangeTime(studentIndex: Int) {
        val iteration = iterationRepository.getCurrentIteration()
        with(studentResults.first { (index, _, _, _) -> index == studentIndex }.initialResolutionTimes) {
            if (size > iteration) {
                get(iteration).add(clockRepository.getElapsedTime())
            } else {
                add(mutableListOf(clockRepository.getElapsedTime()))
            }
        }
    }

    override fun storeResolutionTimeout() {
        val iteration = iterationRepository.getCurrentIteration()
        studentResults.forEach {
            if (it.initialResolutionTimes.size > iteration) {
                it.initialResolutionTimes[iteration].add(clockRepository.getElapsedTime())
            } else {
                it.initialResolutionTimes.add(mutableListOf(clockRepository.getElapsedTime()))
            }
        }
    }

    override suspend fun postAnalytics() {
        postAnalyticsResultsInteractor.postAnalyticsResults(
            analyticsNetworkMapper.mapToNetworkModel(
                results
            )
        )
    }
}