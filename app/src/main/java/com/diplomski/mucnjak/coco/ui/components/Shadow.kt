package com.diplomski.mucnjak.coco.ui.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.diplomski.mucnjak.coco.ui.theme.Dimens

fun Modifier.roundedShadow(
    color: Color,
    offset: Dp = Dimens.x0_5,
    cornerSize: Dp = 0.dp
) = then(
    drawBehind {
        drawIntoCanvas { canvas ->
            canvas.drawRoundRect(
                left = offset.toPx(),
                top = offset.toPx(),
                right = size.width + offset.toPx(),
                bottom = size.height + offset.toPx(),
                paint = Paint().also { it.color = color },
                radiusX = cornerSize.toPx(),
                radiusY = cornerSize.toPx(),
            )
        }
    }
)