package com.diplomski.mucnjak.coco.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.diplomski.mucnjak.coco.ui.components.splitscreen.LocalIsPortrait
import com.diplomski.mucnjak.coco.ui.theme.Dimens

@Composable
fun ColumnScope.AnswersContainer(
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit
) {
    if (LocalIsPortrait.current) {
        Column(
            modifier = modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(Dimens.x3)
        ) {
            content(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    } else {
        Row(
            modifier = modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Dimens.x3)
        ) {
            content(
                Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )
        }
    }
}