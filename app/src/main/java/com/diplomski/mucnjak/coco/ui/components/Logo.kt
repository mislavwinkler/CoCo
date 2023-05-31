package com.diplomski.mucnjak.coco.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.diplomski.mucnjak.coco.R
import com.diplomski.mucnjak.coco.ui.theme.CoCoTheme

private const val LOGO_ALPHA = 0.1f

@Composable
fun Logo(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_logo),
        contentDescription = null,
        alpha = LOGO_ALPHA,
        colorFilter = ColorFilter.tint(Color.Black)
    )
}

@Composable
@Preview(device = SAMSUNG_SM_X200)
private fun PreviewLogo() {
    CoCoTheme {
        Logo()
    }
}

