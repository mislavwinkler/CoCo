package com.diplomski.mucnjak.coco.domain.repositories.students

import com.diplomski.mucnjak.coco.data.ui.Student
import com.diplomski.mucnjak.coco.domain.storages.base.StorageUpdate
import com.diplomski.mucnjak.coco.domain.storages.base.StorageUpdateType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentRepositoryImpl @Inject constructor() : StudentRepository {

    private val students: MutableMap<Int, Student> = mutableMapOf()

    private val studentUpdatesMutableFlow: MutableSharedFlow<StorageUpdate<Student>> = MutableSharedFlow()

    override val studentUpdatesFlow: Flow<StorageUpdate<Student>> = studentUpdatesMutableFlow

    override fun storeStudent(student: Student, studentIndex: Int) {
        students[studentIndex] = student

        studentUpdatesMutableFlow.tryEmit(
            StorageUpdate(
                updateType = StorageUpdateType.INSERT,
                newData = student
            )
        )
    }

    override fun getAllStudents(): List<Student> = students.values.toList()

    override fun getStudent(index: Int) = students[index]

    override suspend fun updateStudent(index: Int, student: Student) {
        val oldData = this.students[index]
        this.students[index] = student

        studentUpdatesMutableFlow.emit(
            StorageUpdate(
                updateType = StorageUpdateType.UPDATE,
                oldData = oldData,
                newData = student
            )
        )
    }
}
