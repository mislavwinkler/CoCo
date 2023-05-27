package com.diplomski.mucnjak.coco.domain.repositories.clock

import com.diplomski.mucnjak.coco.shared.dispatcher.Dispatcher
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClockRepositoryImpl @Inject constructor(
    private val dispatcher: Dispatcher
) : ClockRepository {

    private var timer = 0

    private var currentTimerFLow: MutableSharedFlow<Int> = MutableSharedFlow()

    override val timerFlow: Flow<Int>
        get() = currentTimerFLow

    @OptIn(DelicateCoroutinesApi::class)
    override suspend fun startClock(
        time: Int,
        onTimeout: suspend () -> Unit
    ): SharedFlow<Int> = withContext(dispatcher.io) {
        timer = time
        flow<Unit> {
            // Delay one second for smoother flow
            delay(1000)
            while (this@ClockRepositoryImpl.timer >= 0) {
                this@ClockRepositoryImpl.currentTimerFLow.emit(this@ClockRepositoryImpl.timer)
                delay(1000)
                this@ClockRepositoryImpl.timer--
            }
            onTimeout()
        }.launchIn(GlobalScope)

        return@withContext currentTimerFLow
    }
}