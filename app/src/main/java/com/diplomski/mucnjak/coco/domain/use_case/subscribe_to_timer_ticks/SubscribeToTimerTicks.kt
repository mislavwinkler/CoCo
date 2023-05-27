package com.diplomski.mucnjak.coco.domain.use_case.subscribe_to_timer_ticks

import kotlinx.coroutines.flow.Flow

interface SubscribeToTimerTicks {

    suspend operator fun invoke(): Flow<Int>
}