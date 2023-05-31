package com.diplomski.mucnjak.coco.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = DarkPurple,
    primaryVariant = LightPurple,
    secondary = Purple,
    secondaryVariant = Purple,
    background = White,
    surface = LightPurple,
    onPrimary = White,
    onSecondary = White,
    onBackground = LightPurple,
    onSurface = White,
    error = Pink,
    onError = White
)

private val LightColorPalette = lightColors(
    primary = DarkPurple,
    primaryVariant = LightPurple,
    secondary = Purple,
    secondaryVariant = Purple,
    background = White,
    surface = LightPurple,
    onPrimary = White,
    onSecondary = White,
    onBackground = LightPurple,
    onSurface = White,
    error = Pink,
    onError = White
)

private val Student2DarkColorPalette = darkColors(
    primary = DarkBlue,
    primaryVariant = LightBlue,
    secondary = Blue,
    secondaryVariant = Teal,
    background = White,
    surface = LightBlue,
    onPrimary = White,
    onSecondary = White,
    onBackground = LightBlue,
    onSurface = White,
    error = Pink,
    onError = White
)

private val Student2LightColorPalette = lightColors(
    primary = DarkBlue,
    primaryVariant = LightBlue,
    secondary = Blue,
    secondaryVariant = Teal,
    background = White,
    surface = LightBlue,
    onPrimary = White,
    onSecondary = White,
    onBackground = LightBlue,
    onSurface = White,
    error = Pink,
    onError = White
)

private val Student3DarkColorPalette = darkColors(
    primary = DarkYellow,
    primaryVariant = LightYellow,
    secondary = Yellow,
    secondaryVariant = Yellow,
    background = White,
    surface = LightYellow,
    onPrimary = White,
    onSecondary = White,
    onBackground = LightYellow,
    onSurface = White,
    error = Pink,
    onError = White
)

private val Student3LightColorPalette = lightColors(
    primary = DarkYellow,
    primaryVariant = LightYellow,
    secondary = Yellow,
    secondaryVariant = Yellow,
    background = White,
    surface = LightYellow,
    onPrimary = White,
    onSecondary = White,
    onBackground = LightYellow,
    onSurface = White,
    error = Pink,
    onError = White
)

private val Student4DarkColorPalette = darkColors(
    primary = DarkOrange,
    primaryVariant = LightOrange,
    secondary = Orange,
    secondaryVariant = Orange,
    background = White,
    surface = LightOrange,
    onPrimary = White,
    onSecondary = White,
    onBackground = LightOrange,
    onSurface = White,
    error = Pink,
    onError = White
)

private val Student4LightColorPalette = lightColors(
    primary = DarkOrange,
    primaryVariant = LightOrange,
    secondary = Orange,
    secondaryVariant = Orange,
    background = White,
    surface = LightOrange,
    onPrimary = White,
    onSecondary = White,
    onBackground = LightOrange,
    onSurface = White,
    error = Pink,
    onError = White
)

@Composable
fun CoCoTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider(LocalCustomColor provides CustomColorPalette) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

@Composable
private fun Student1CoCoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider(LocalCustomColor provides CustomColorPalette) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

@Composable
private fun Student2CoCoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        Student2DarkColorPalette
    } else {
        Student2LightColorPalette
    }

    CompositionLocalProvider(LocalCustomColor provides CustomColorPalette) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

@Composable
private fun Student3CoCoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        Student3DarkColorPalette
    } else {
        Student3LightColorPalette
    }

    CompositionLocalProvider(LocalCustomColor provides CustomColorPalette) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

@Composable
private fun Student4CoCoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        Student4DarkColorPalette
    } else {
        Student4LightColorPalette
    }

    CompositionLocalProvider(LocalCustomColor provides CustomColorPalette) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

@Composable
fun StudentCoCoTheme(studentIndex: Int, content: @Composable () -> Unit) {
    when (studentIndex) {
        1 -> Student2CoCoTheme(content = content)
        2 -> Student3CoCoTheme(content = content)
        3 -> Student4CoCoTheme(content = content)
        else -> Student1CoCoTheme(content = content)
    }
}

object CustomColorPalette {
    val neutralBackground: Color = VeryLightPurple
    val buttonShadow: Color = DarkPink
}

val LocalCustomColor: ProvidableCompositionLocal<CustomColorPalette> = compositionLocalOf { CustomColorPalette }
