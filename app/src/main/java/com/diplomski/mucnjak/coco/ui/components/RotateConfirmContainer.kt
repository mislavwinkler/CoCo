package com.diplomski.mucnjak.coco.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RotateConfirmContainer(
    modifier: Modifier = Modifier,
    onRotate: () -> Unit,
    onConfirm: () -> Unit,
    isConfirmed: Boolean = false,
) {
    Row(
        modifier = Modifier.fillMaxWidth().then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RotateButton { onRotate() }
        ConfirmButton(isConfirmed = isConfirmed) { onConfirm() }
    }
}