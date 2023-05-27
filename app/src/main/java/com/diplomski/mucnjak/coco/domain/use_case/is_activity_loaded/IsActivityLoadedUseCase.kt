package com.diplomski.mucnjak.coco.domain.use_case.is_activity_loaded

import com.diplomski.mucnjak.coco.domain.repositories.active_activity.ActiveActivityRepository
import javax.inject.Inject

class IsActivityLoadedUseCase @Inject constructor(
    private val activeActivityRepository: ActiveActivityRepository
) : IsActivityLoaded {

    override fun invoke() = activeActivityRepository.getLocalActiveActivity() != null
}