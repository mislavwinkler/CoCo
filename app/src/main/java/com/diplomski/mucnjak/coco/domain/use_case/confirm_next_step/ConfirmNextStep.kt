package com.diplomski.mucnjak.coco.domain.use_case.confirm_next_step

interface ConfirmNextStep {

    suspend operator fun invoke(studentIndex: Int)
}