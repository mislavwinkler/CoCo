package com.diplomski.mucnjak.coco.domain.use_case.get_next_time

interface GetNextTime {

    suspend operator fun invoke(): Int
}