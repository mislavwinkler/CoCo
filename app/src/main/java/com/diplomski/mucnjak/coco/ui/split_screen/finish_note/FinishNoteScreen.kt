package com.diplomski.mucnjak.coco.ui.split_screen.finish_note

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.diplomski.mucnjak.coco.R
import com.diplomski.mucnjak.coco.extensions.addWithSize
import com.diplomski.mucnjak.coco.ui.ComposeMock
import com.diplomski.mucnjak.coco.ui.theme.CoCoTheme
import com.diplomski.mucnjak.coco.ui.theme.Dimens

@Composable
fun FinishNoteScreen() {
    Content()
}

@Composable
private fun Content() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .addWithSize(
                    addOnLarge = { fillMaxWidth(0.5f) },
                    addOnSmall = { fillMaxWidth(0.7f) }
                )
                .align(Alignment.Center)
        ) {
            Text(
                text = stringResource(R.string.practice_finished),
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.primary,
            )
            Text(
                modifier = Modifier.padding(top = Dimens.x5),
                text = stringResource(R.string.finish_description),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary,
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
private fun PreviewFinishNoteContent() {
    CoCoTheme {
        Content()
    }
}
