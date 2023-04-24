package com.diplomski.mucnjak.coco.domain.use_case.get_all_answers

import com.diplomski.mucnjak.coco.data.ui.Answer

interface GetAllAnswers {
    suspend operator fun invoke(): List<Answer>
}