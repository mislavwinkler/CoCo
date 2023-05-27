package com.diplomski.mucnjak.coco.domain.use_case.subscribe_to_timer_ticks

import com.diplomski.mucnjak.coco.domain.repositories.clock.ClockRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubscribeToTimerTicksUseCase @Inject constructor(
    private val clockRepository: ClockRepository,
) : SubscribeToTimerTicks {

    override suspend fun invoke(): Flow<Int> = clockRepository.timerFlow
}