package com.diplomski.mucnjak.coco.ui.split_screen.finish_note

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun FinishNoteScreen() {
    Content()
}

@Composable
private fun Content() {
    Column {
        Text(
            text = "Practice is finished!",
            style = MaterialTheme.typography.h3,
        )
        Text(
            text = "Let's go see your results",
            style = MaterialTheme.typography.body1,
        )
    }
}