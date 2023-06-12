package com.diplomski.mucnjak.coco.data.domain

data class StudentResultsDomainModel(
    val studentIndex: Int,
    val name: String,
    val accuracies: MutableList<Int>,
    val initialResolutionTimes: MutableList<MutableList<Int>>,
)
