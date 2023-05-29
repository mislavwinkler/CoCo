package com.diplomski.mucnjak.coco.domain.use_case.revoke_next_step_confirmation

interface RevokeNextStepConfirmation {

    suspend operator fun invoke(studentIndex: Int)
}