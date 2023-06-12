package com.diplomski.mucnjak.coco.domain.repositories.iteration

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IterationRepositoryImpl @Inject constructor() : IterationRepository {

    private var iteration = -1

    override fun reset() {
        iteration = -1
    }

    override fun getCurrentIteration(): Int = iteration

    override fun incrementCurrentIteration() {
        iteration++
    }

    override fun setCurrentIteration(iteration: Int) {
        this.iteration = iteration
    }
}
