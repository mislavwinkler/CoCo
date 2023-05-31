package com.diplomski.mucnjak.coco.domain.use_case.get_num_of_students

import com.diplomski.mucnjak.coco.domain.repositories.active_activity.ActiveActivityRepository
import javax.inject.Inject

class GetNumOfStudentsUseCase @Inject constructor(
    private val activityRepository: ActiveActivityRepository
) : GetNumOfStudents {

    override suspend fun invoke(): Int = activityRepository.getActiveActivity().activeNumOfStudents
}