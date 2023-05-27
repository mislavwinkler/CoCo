package com.diplomski.mucnjak.coco.domain.use_case.get_available_question

import com.diplomski.mucnjak.coco.data.ui.Question

interface GetAvailableQuestion {
    suspend operator fun invoke(studentIndex: Int): Question
}