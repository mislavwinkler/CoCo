package com.diplomski.mucnjak.coco.domain.use_case.get_all_questions

import com.diplomski.mucnjak.coco.data.ui.Question

interface GetAllQuestions {

    suspend operator fun invoke(): List<Question>
}