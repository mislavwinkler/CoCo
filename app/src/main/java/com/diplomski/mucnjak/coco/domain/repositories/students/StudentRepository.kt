package com.diplomski.mucnjak.coco.domain.repositories.students

import com.diplomski.mucnjak.coco.data.domain.StudentAnswers
import com.diplomski.mucnjak.coco.data.ui.Answer
import com.diplomski.mucnjak.coco.data.ui.Student
import com.diplomski.mucnjak.coco.domain.storages.base.StorageUpdate
import kotlinx.coroutines.flow.Flow

interface StudentRepository {

    suspend fun storeStudent(student: Student, studentIndex: Int)
    val studentUpdatesFlow: Flow<StorageUpdate<Student>>
    fun getAllStudents(): List<Student>
    suspend fun updateStudent(index: Int, student: Student)
    fun getStudent(index: Int): Student?
    fun addAnswer(studentIndex: Int, answer: Answer)
    fun removeAnswer(studentIndex: Int, answer: Answer)
    fun getAllStudentsAnswers(): List<StudentAnswers>
    fun getStudentAnswers(studentIndex: Int): List<Answer>
    fun clearAllStudentAnswers()
}