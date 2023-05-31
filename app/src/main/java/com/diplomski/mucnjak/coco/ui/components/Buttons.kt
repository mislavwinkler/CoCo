package com.diplomski.mucnjak.coco.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diplomski.mucnjak.coco.R
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.ui.theme.CoCoTheme
import com.diplomski.mucnjak.coco.ui.theme.LocalCustomColor
import com.diplomski.mucnjak.coco.ui.theme.Dimens

@Composable
fun ConfirmButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier.size(Dimens.x8),
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.primaryVariant
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_check),
            contentDescription = null,
        )
    }
}

@Composable
fun RotateButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier
            .size(Dimens.x8),
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.primary
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = (-1).dp,
            disabledElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_rotate),
            contentDescription = null,
        )
    }
}

@Composable
fun CocoButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.error
        ),
        contentPadding = PaddingValues(horizontal = Dimens.x5, vertical = Dimens.x1)
    ) {
        Text(
            text = text.toUpperCase(Locale.current),
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.onSurface,
        )
    }
}

@Composable
fun StartButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .roundedShadow(
                color = LocalCustomColor.current.buttonShadow,
                cornerSize = Dimens.x1,
            ),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.error
        ),
        contentPadding = PaddingValues(horizontal = Dimens.x5, vertical = Dimens.x1)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text.toUpperCase(Locale.current),
                style = MaterialTheme.typography.button,
                color = MaterialTheme.colors.onSurface,
            )
            Image(
                modifier = Modifier
                    .padding(start = Dimens.x2)
                    .size(Dimens.x3),
                painter = painterResource(id = R.drawable.ic_arrow_start),
                contentDescription = null,
            )
        }
    }
}

@Preview(device = SAMSUNG_SM_X200)
@Composable
private fun PreviewConfirmButton() {
    CoCoTheme {
        ConfirmButton {}
    }
}

@Preview(device = SAMSUNG_SM_X200)
@Composable
private fun PreviewRotateButton() {
    CoCoTheme {
        RotateButton {}
    }
}

@Preview(device = SAMSUNG_SM_X200)
@Composable
private fun PreviewCocoButton() {
    CoCoTheme {
        CocoButton(text = "BUTTON") { DoNothing }
    }
}

@Preview(device = SAMSUNG_SM_X200)
@Composable
private fun PreviewStartButton() {
    CoCoTheme {
        StartButton(text = "BUTTON") { DoNothing }
    }
}
