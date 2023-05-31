package com.diplomski.mucnjak.coco.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import com.diplomski.mucnjak.coco.R
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.ui.ComposeMock
import com.diplomski.mucnjak.coco.ui.theme.CoCoTheme
import com.diplomski.mucnjak.coco.ui.theme.LocalCustomColor
import com.diplomski.mucnjak.coco.ui.theme.Dimens

@Composable
fun ConfirmButton(
    modifier: Modifier = Modifier,
    isConfirmed: Boolean = false,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.size(Dimens.x8),
        enabled = isConfirmed.not(),
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.secondaryVariant,
            disabledBackgroundColor = MaterialTheme.colors.secondaryVariant,
            disabledContentColor = MaterialTheme.colors.background,
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
            defaultElevation = Dimens.zero,
            pressedElevation = Dimens.zero,
            disabledElevation = Dimens.zero,
            hoveredElevation = Dimens.zero,
            focusedElevation = Dimens.zero
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
        contentPadding = PaddingValues(horizontal = Dimens.x5, vertical = Dimens.x1),
        elevation = ButtonDefaults.elevation(defaultElevation = Dimens.zero),
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
                cornerShape = RoundedCornerShape(Dimens.x1)
            ),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.error
        ),
        contentPadding = PaddingValues(horizontal = Dimens.x5, vertical = Dimens.x2),
        elevation = ButtonDefaults.elevation(defaultElevation = Dimens.zero),
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

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        elevation = ButtonDefaults.elevation(
            defaultElevation = Dimens.zero,
            pressedElevation = Dimens.zero,
            disabledElevation = Dimens.zero,
            hoveredElevation = Dimens.zero
        ),
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colors.secondaryVariant
        )
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = null,
            colorFilter = ColorFilter.tint(LocalContentColor.current)
        )
        Text(
            modifier = Modifier.padding(start = Dimens.x1),
            text = text,
            style = MaterialTheme.typography.body1
        )
    }
}

@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
@Composable
private fun PreviewConfirmButton() {
    CoCoTheme {
        ConfirmButton {}
    }
}

@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
@Composable
private fun PreviewConfirmConfirmedButton() {
    CoCoTheme {
        ConfirmButton(isConfirmed = true) {}
    }
}

@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
@Composable
private fun PreviewRotateButton() {
    CoCoTheme {
        RotateButton {}
    }
}

@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
@Composable
private fun PreviewCocoButton() {
    CoCoTheme {
        CocoButton(text = ComposeMock.LOREM_IPSUM) { DoNothing }
    }
}

@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
@Composable
private fun PreviewStartButton() {
    CoCoTheme {
        StartButton(text = ComposeMock.LOREM_IPSUM) { DoNothing }
    }
}
