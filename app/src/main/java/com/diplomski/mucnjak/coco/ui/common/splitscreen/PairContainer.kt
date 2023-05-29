package com.diplomski.mucnjak.coco.ui.common.splitscreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PairContainer(
    screens: List<@Composable (RowScope.(rotation: Float) -> Unit)>
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        screens[0](0f)
        screens[1](0f)
    }
}