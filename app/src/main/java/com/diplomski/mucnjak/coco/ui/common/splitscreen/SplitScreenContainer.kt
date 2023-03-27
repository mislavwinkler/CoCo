package com.diplomski.mucnjak.coco.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import com.diplomski.mucnjak.coco.extensions.pxToDp

@Composable
fun SplitScreenContainer(
    numOfScreens: Int = 4,
    screen: @Composable (index: Int) -> Unit
) {
    Box(Modifier.fillMaxSize()) {
        val screens = buildList<@Composable RowScope.(rotation: Float) -> Unit> {
            repeat(numOfScreens) { index ->
                add { rotation ->
                    var size by remember(key1 = IntSize(0, 0)) { mutableStateOf(IntSize(0, 0)) }
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .onSizeChanged { size = it }
                    ) {
                        Box(
                            if (rotation % 180 != 0f) {
                                Modifier
                                    .rotate(rotation)
                                    .requiredHeight(size.width.pxToDp())
                                    .requiredWidth(size.height.pxToDp())
                            } else {
                                Modifier.rotate(rotation)
                            }
                        ) {
                            screen(index)
                        }
                    }
                }
            }
        }
        when (numOfScreens) {
            1 -> Row { screen(0) }
            2 -> PairContainer(screens = screens)
            else -> GridContainer(screens = screens)
        }
    }
}
