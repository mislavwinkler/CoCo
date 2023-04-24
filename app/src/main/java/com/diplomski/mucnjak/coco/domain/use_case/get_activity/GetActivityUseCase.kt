package com.diplomski.mucnjak.coco.domain.use_case.get_activity

import com.diplomski.mucnjak.coco.domain.mapper.activity.ActivityMappers
import com.diplomski.mucnjak.coco.domain.repositories.active_activity.ActiveActivityRepository
import javax.inject.Inject

class GetActivityUseCase @Inject constructor(
    private val activeActivityRepository: ActiveActivityRepository,
    private val activityMapper: ActivityMappers.ActivityMapper
) : GetActivity {

    override suspend fun invoke() =
        activeActivityRepository.getActiveActivity().let { activityMapper.mapToUiModel(it) }
}