package com.diplomski.mucnjak.coco.domain.repositories.iteration

interface IterationRepository {
    fun reset()
    fun getCurrentIteration(): Int
    fun incrementCurrentIteration()
    fun setCurrentIteration(iteration: Int)
}