package com.diplomski.mucnjak.coco.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.diplomski.mucnjak.coco.R

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.surface,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            AnimatedLogo()
            Text(
                text = "LOADING",
                color = MaterialTheme.colors.secondary
            )
        }
    }
}

@Composable
private fun AnimatedLogo() {
    val infiniteTransition = rememberInfiniteTransition()
    val frame by infiniteTransition.animateValue(
        initialValue = 0,
        targetValue = 4,
        typeConverter = Int.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Restart
        )
    )
    val frames = listOf(
        R.drawable.ic_logo_loading_1,
        R.drawable.ic_logo_loading_2,
        R.drawable.ic_logo_loading_3,
        R.drawable.ic_logo_loading_4,
    )
    Image(
        modifier = Modifier.fillMaxHeight(fraction = 0.5f),
        painter = painterResource(id = frames[frame]),
        contentDescription = null,
    )
}
