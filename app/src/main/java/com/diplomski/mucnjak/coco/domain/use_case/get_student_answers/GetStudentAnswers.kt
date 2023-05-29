package com.diplomski.mucnjak.coco.domain.use_case.get_student_answers

import com.diplomski.mucnjak.coco.data.ui.Answer

interface GetStudentAnswers {

    operator fun invoke(studentIndex: Int): List<Answer>
}