package com.diplomski.mucnjak.coco.domain.use_case.release_activity

import com.diplomski.mucnjak.coco.domain.interactor.release_activity_config.ReleaseActivityConfigInteractor
import com.diplomski.mucnjak.coco.domain.repositories.active_activity.ActiveActivityRepository
import com.diplomski.mucnjak.coco.domain.repositories.question.QuestionsRepository
import javax.inject.Inject

class ReleaseActivityUseCase @Inject constructor(
    private val releaseActivityConfigInteractor: ReleaseActivityConfigInteractor,
    private val activeActivityRepository: ActiveActivityRepository,
    private val questionsRepository: QuestionsRepository,
) : ReleaseActivity {

    override suspend fun invoke() {
        releaseActivityConfigInteractor.releaseActivityConfig()
        activeActivityRepository.clearRepository()
        questionsRepository.clearRepository()
    }
}