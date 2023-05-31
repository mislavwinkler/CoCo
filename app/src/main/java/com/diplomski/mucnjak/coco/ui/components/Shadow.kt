package com.diplomski.mucnjak.coco.ui.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpOffset
import com.diplomski.mucnjak.coco.ui.theme.Dimens

fun Modifier.roundedShadow(
    color: Color,
    offset: DpOffset = DpOffset(x = Dimens.x0_75, y = Dimens.x0_75),
    cornerShape: Shape = RectangleShape
): Modifier = then(
    drawBehind {
        drawIntoCanvas { canvas ->
            val path = Path().apply {
                addOutline(
                    outline = cornerShape.createOutline(
                        size = Size(width = size.width, height = size.height),
                        layoutDirection = layoutDirection,
                        density = Density(density, fontScale)
                    )
                )
                this.translate(offset = Offset(x = offset.x.toPx(), y = offset.y.toPx()))
            }
            canvas.drawPath(path = path, paint = Paint().also { paint -> paint.color = color })
        }
    }
)