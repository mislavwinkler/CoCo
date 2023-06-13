package com.diplomski.mucnjak.coco.domain.use_case.get_student_rotation

import com.diplomski.mucnjak.coco.domain.repositories.students.StudentRepository
import com.diplomski.mucnjak.coco.extensions.isNotNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SubscribeToStudentRotationUseCase @Inject constructor(
    private val studentRepository: StudentRepository,
) : SubscribeToStudentRotation {

    override fun invoke(index: Int): Flow<Int> =
        studentRepository.studentUpdatesFlow.filter { (_, oldData, newData) ->

            oldData.isNotNull() && newData.isNotNull()
                    && (oldData?.position == index || newData?.position == index)
                    && oldData?.rotation != newData?.rotation
        }.map {
            it.newData?.rotation ?: 0
        }
}