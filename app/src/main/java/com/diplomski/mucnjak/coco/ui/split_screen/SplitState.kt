package com.diplomski.mucnjak.coco.ui.split_screen

sealed class SplitState {

    data class Initial(val numOfStudents: Int, val rotations: List<Int>): SplitState()

    object Finished: SplitState()
}