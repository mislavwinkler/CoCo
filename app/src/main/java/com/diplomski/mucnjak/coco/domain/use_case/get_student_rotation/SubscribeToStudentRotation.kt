package com.diplomski.mucnjak.coco.domain.use_case.get_student_rotation

import kotlinx.coroutines.flow.Flow

interface SubscribeToStudentRotation {
    operator fun invoke(index: Int): Flow<Int>
}