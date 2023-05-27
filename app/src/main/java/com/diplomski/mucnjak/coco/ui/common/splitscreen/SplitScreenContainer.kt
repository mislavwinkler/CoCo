package com.diplomski.mucnjak.coco.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import com.diplomski.mucnjak.coco.extensions.pxToDp

val LocalSize = compositionLocalOf { IntSize(0, 0) }

@Composable
fun SplitScreenContainer(
    numOfScreens: Int = 4,
    rotations: List<Int>,
    screen: @Composable (index: Int) -> Unit
) {
    Box(Modifier.fillMaxSize()) {
        val screens = buildList<@Composable RowScope.(rotation: Float) -> Unit> {
            repeat(numOfScreens) { index ->
                add { rotation ->
                    val localRotation = rotation + rotations[index]
                    var size by remember(key1 = IntSize(0, 0)) { mutableStateOf(IntSize(0, 0)) }
                    CompositionLocalProvider(LocalSize provides size) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .onSizeChanged { size = it }
                        ) {
                            Box(
                                if (localRotation % 180 != 0f) {
                                    Modifier
                                        .rotate(localRotation)
                                        .requiredHeight(size.width.pxToDp())
                                        .requiredWidth(size.height.pxToDp())
                                } else {
                                    Modifier.rotate(localRotation)
                                }
                            ) {
                                screen(index)
                            }
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
