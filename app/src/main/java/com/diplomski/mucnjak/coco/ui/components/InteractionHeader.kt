package com.diplomski.mucnjak.coco.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.diplomski.mucnjak.coco.extensions.empty
import com.diplomski.mucnjak.coco.ui.ComposeMock

@Composable
fun InteractionHeader(
    modifier: Modifier = Modifier,
    title: String,
    time: String = String.empty,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary,
        )
        if (time.isNotBlank()) {
            Text(
                text = time,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.primary,
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
private fun PreviewInteractionHeader() {
    InteractionHeader(
        title = ComposeMock.STUDENT_NAME,
        time = ComposeMock.TIME
    )
}
