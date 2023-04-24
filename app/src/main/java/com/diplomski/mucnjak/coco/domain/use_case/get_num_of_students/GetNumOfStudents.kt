package com.diplomski.mucnjak.coco.domain.use_case.get_num_of_students

interface GetNumOfStudents {
    suspend operator fun invoke(): Int
}