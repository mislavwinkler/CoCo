package com.diplomski.mucnjak.coco.domain.repositories.students

import com.diplomski.mucnjak.coco.data.domain.StudentAnswers
import com.diplomski.mucnjak.coco.data.ui.Answer
import com.diplomski.mucnjak.coco.data.ui.Student
import com.diplomski.mucnjak.coco.domain.repositories.question.QuestionsRepository
import com.diplomski.mucnjak.coco.domain.storages.base.StorageUpdate
import com.diplomski.mucnjak.coco.domain.storages.base.StorageUpdateType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentRepositoryImpl @Inject constructor(
    private val questionRepository: QuestionsRepository
) : StudentRepository {

    private val students: MutableMap<Int, Student> = mutableMapOf()

    private val studentUpdatesMutableFlow: MutableSharedFlow<StorageUpdate<Student>> =
        MutableSharedFlow()

    override val studentUpdatesFlow: Flow<StorageUpdate<Student>> = studentUpdatesMutableFlow

    private val studentAnswers: MutableMap<Int, StudentAnswers> = mutableMapOf()

    override suspend fun storeStudent(student: Student, studentIndex: Int) {
        students[studentIndex] = student
        studentAnswers[studentIndex] = StudentAnswers(
            studentIndex = studentIndex,
            question = questionRepository.getAvailableQuestion(studentIndex)
        )

        studentUpdatesMutableFlow.tryEmit(
            StorageUpdate(
                updateType = StorageUpdateType.INSERT,
                newData = student
            )
        )
    }

    override fun getAllStudents(): List<Student> = students.values.toList()

    override fun getStudent(index: Int): Student? = students[index]

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

    override fun addAnswer(studentIndex: Int, answer: Answer) {
        studentAnswers[studentIndex]?.answers?.add(answer)
    }

    override fun removeAnswer(studentIndex: Int, answer: Answer) {
        studentAnswers[studentIndex]?.answers?.remove(answer)
    }

    override fun getAllStudentsAnswers(): List<StudentAnswers> = studentAnswers.values.toList()

    override fun getStudentAnswers(studentIndex: Int): List<Answer> =
        studentAnswers[studentIndex]?.answers.orEmpty()

    override fun clearAllStudentAnswers() {
        studentAnswers.forEach {
            it.value.answers.clear()
        }
    }
}
