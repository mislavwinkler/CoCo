package com.diplomski.mucnjak.coco.domain.use_case.store_student

import com.diplomski.mucnjak.coco.data.ui.Student

interface StoreStudent {

    suspend operator fun invoke(student: Student, studentIndex: Int)
}