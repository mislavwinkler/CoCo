package com.diplomski.mucnjak.coco.extensions

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.diplomski.mucnjak.coco.ui.split_screen.LocalIsLarge

@SuppressLint("ComposableModifierFactory")
@Composable
fun Modifier.addWithSize(
    addOnLarge: @Composable Modifier.() -> Modifier = { this },
    addOnSmall: @Composable Modifier.() -> Modifier = { this }
): Modifier = this.then(
    if (LocalIsLarge.current) {
        addOnLarge()
    } else {
        addOnSmall()
    }
)