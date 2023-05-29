package com.diplomski.mucnjak.coco.domain.use_case.add_incorrect_answer_info

import com.diplomski.mucnjak.coco.data.ui.Answer

interface AddIncorrectAnswerInfo {

    operator fun invoke(answers: List<Answer>): List<Answer>
}