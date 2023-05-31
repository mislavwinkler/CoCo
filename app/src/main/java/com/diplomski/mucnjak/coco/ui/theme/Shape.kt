package com.diplomski.mucnjak.coco.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable

val Shapes: Shapes = Shapes(
    small = RoundedCornerShape(Dimens.roundCornerSmall),
    medium = RoundedCornerShape(Dimens.roundCornerMedium),
    large = RoundedCornerShape(Dimens.roundCornerLarge)
)

@Composable
fun cardShape(): RoundedCornerShape = RoundedCornerShape(
    topStart = MaterialTheme.shapes.small.topStart,
    topEnd = MaterialTheme.shapes.large.topEnd,
    bottomEnd = MaterialTheme.shapes.small.topEnd,
    bottomStart = MaterialTheme.shapes.large.bottomStart,
)