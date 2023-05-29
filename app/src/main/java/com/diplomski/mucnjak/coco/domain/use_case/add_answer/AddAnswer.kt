package com.diplomski.mucnjak.coco.domain.use_case.add_answer

import com.diplomski.mucnjak.coco.data.ui.Answer

interface AddAnswer {

    operator fun invoke(studentIndex: Int, answer: Answer)
}