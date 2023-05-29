package com.diplomski.mucnjak.coco.domain.use_case.remove_answer

import com.diplomski.mucnjak.coco.data.ui.Answer

interface RemoveAnswer {

    operator fun invoke(studentIndex: Int, answer: Answer)
}