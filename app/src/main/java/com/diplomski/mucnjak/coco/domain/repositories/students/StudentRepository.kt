package com.diplomski.mucnjak.coco.domain.repositories.students

import com.diplomski.mucnjak.coco.data.ui.Student
import com.diplomski.mucnjak.coco.domain.storages.base.StorageUpdate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface StudentRepository {

    fun storeStudent(student: Student, studentIndex: Int)
    val studentUpdatesFlow: Flow<StorageUpdate<Student>>
    fun getAllStudents(): List<Student>
    suspend fun updateStudent(index: Int, student: Student)
    fun getStudent(index: Int): Student?
}