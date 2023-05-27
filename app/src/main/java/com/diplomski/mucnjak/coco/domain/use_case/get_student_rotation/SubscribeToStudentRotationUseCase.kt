package com.diplomski.mucnjak.coco.domain.use_case.get_student_rotation

import com.diplomski.mucnjak.coco.domain.repositories.students.StudentRepository
import com.diplomski.mucnjak.coco.extensions.isNotNull
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SubscribeToStudentRotationUseCase @Inject constructor(
    private val studentRepository: StudentRepository,
) : SubscribeToStudentRotation {

    override fun invoke(index: Int) = studentRepository.studentUpdatesFlow.filter { storageUpdate ->

        storageUpdate.oldData.isNotNull() && storageUpdate.newData.isNotNull()
                && (storageUpdate.oldData?.position == index || storageUpdate.newData?.position == index)
                && storageUpdate.oldData?.rotation != storageUpdate.newData?.rotation
    }.map {
        it.newData?.rotation ?: 0
    }
}