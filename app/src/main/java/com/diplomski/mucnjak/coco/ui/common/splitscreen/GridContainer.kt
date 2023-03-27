package com.diplomski.mucnjak.coco.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GridContainer(
    screens: List<@Composable (RowScope.(rotation: Float) -> Unit)>
) {
    Column(
        Modifier.fillMaxSize()
    ) {
        repeat(if (screens.size <= 1) 1 else 2) { columnIndex ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                if (columnIndex == 0) {
                    repeat(screens.size / 2) { rowIndex ->
                        screens[rowIndex](180f)
                    }
                } else {
                    repeat(screens.size - screens.size / 2) { rowIndex ->
                        screens[screens.size / 2 + rowIndex](0f)
                    }
                }
            }
        }
    }
}