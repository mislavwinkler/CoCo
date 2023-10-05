package com.diplomski.mucnjak.coco.ui.components.splitscreen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun GridContainer(
    screens: List<@Composable (RowScope.(rotation: Float) -> Unit)>
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(if (screens.size <= 1) 1 else 2) { columnIndex ->
            Row(
                Modifier
                    .run {
                        if (screens.size == 3 && columnIndex == 0) {
                            fillMaxWidth(0.5f)
                        } else {
                            fillMaxWidth()
                        }
                    }
                    .weight(1f),
            ) {
                if (columnIndex == 0) {
                    repeat(screens.size / 2) { rowIndex ->
                        this@Row.apply { screens[rowIndex](180f) }
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
