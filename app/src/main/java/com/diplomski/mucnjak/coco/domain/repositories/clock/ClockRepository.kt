package com.diplomski.mucnjak.coco.domain.repositories.clock

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface ClockRepository {

    suspend fun startClock(time: Int, onTimeout: suspend () -> Unit): SharedFlow<Int>

    val timerFlow: Flow<Int>
    fun cancelTimer()
    fun getElapsedTime(): Int
}