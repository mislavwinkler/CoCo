package com.diplomski.mucnjak.coco.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.withStyle
import com.diplomski.mucnjak.coco.extensions.empty

@Suppress("SpellCheckingInspection")
const val QWERTZ_LAYOUT: String = "QWERTZUIOPASDFGHJKLYXCVBNM"

@Composable
fun CustomKeyboardManagerScope.CustomKeyboard(onConfirm: () -> Unit = {}) {
    val onKey = { char: Char ->
        when (char) {
            '<' -> update(inputText.dropLast(1))
            '>' -> update(isKeyboardShown = false)
            else -> update(inputText + char)
        }
    }
    if (isKeyboardShown) {
        Column {
            Row(modifier = Modifier.align(Alignment.End)) {
                Button(onClick = { onKey('<') }) {
                    Text(text = "<")
                }
                Button(onClick = {
                    onKey('>')
                    onConfirm()
                }) {
                    Text(text = "Done")
                }
            }
            Row {
                for (letter in 0 until 10) {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { onKey(QWERTZ_LAYOUT[letter]) }) {
                        Text(text = "${QWERTZ_LAYOUT[letter]}")
                    }
                }
            }
            Row {
                for (letter in 10 until 19) {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { onKey(QWERTZ_LAYOUT[letter]) }) {
                        Text(text = "${QWERTZ_LAYOUT[letter]}")
                    }
                }
            }
            Row {
                for (letter in 19 until 26) {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { onKey(QWERTZ_LAYOUT[letter]) }) {
                        Text(text = "${QWERTZ_LAYOUT[letter]}")
                    }
                }
            }
        }
    }
}

@Composable
fun CustomKeyboardManagerScope.CustomKeyboardTextField(
    modifier: Modifier = Modifier,
    placeholder: String = String.empty
) {
    val infiniteTransition = rememberInfiniteTransition()
    val cursorBlink by infiniteTransition.animateValue(
        initialValue = 0,
        targetValue = 2,
        typeConverter = Int.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(500),
            repeatMode = RepeatMode.Reverse
        )
    )
    val displayText = if (isKeyboardShown && cursorBlink == 1) {
        "${inputText}|"
    } else {
        inputText
    }
    TextField(
        modifier = modifier
            .focusable(false)
            .clickable { update(isKeyboardShown = true) },
        value = displayText,
        onValueChange = {},
        enabled = false,
        placeholder = { if (!isKeyboardShown) Text(text = placeholder) },
        visualTransformation = { text ->
            TransformedText(
                AnnotatedString.Builder().apply {
                    if (text.contains('|')) {
                        append(text.filter { it != '|' })
                        withStyle(style = SpanStyle(color = Color.Magenta)) {
                            append("|")
                        }
                    } else {
                        append(text)
                    }
                }.toAnnotatedString(),
                OffsetMapping.Identity
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            disabledTextColor = Color.Black
        )
    )
}

data class CustomKeyboardManagerScope(
    val inputText: String = "",
    val isKeyboardShown: Boolean = false,
    val onUpdated: (CustomKeyboardManagerScope) -> Unit = {},
) {
    fun update(inputText: String? = null, isKeyboardShown: Boolean? = null): Unit = onUpdated(
        CustomKeyboardManagerScope(
            inputText ?: this.inputText,
            isKeyboardShown ?: this.isKeyboardShown,
            onUpdated
        )
    )
}

@Composable
fun CustomKeyboardContainer(content: @Composable CustomKeyboardManagerScope.() -> Unit) {
    var customKeyboardManager by rememberSaveable(stateSaver = object :
        Saver<CustomKeyboardManagerScope?, String> {
        override fun restore(value: String): CustomKeyboardManagerScope? {
            if (value.isEmpty()) {
                return null
            }
            return CustomKeyboardManagerScope(value.drop(1), value.take(1) == "1")
        }

        override fun SaverScope.save(value: CustomKeyboardManagerScope?): String {
            if (value == null) {
                return ""
            }
            val isKeyboardShownCode = if (value.isKeyboardShown) "1" else "0"
            return "$isKeyboardShownCode${value.inputText}"
        }
    }) { mutableStateOf(null) }

    if (customKeyboardManager == null) {
        customKeyboardManager = CustomKeyboardManagerScope(
            "",
            isKeyboardShown = false
        ) { updated -> customKeyboardManager = updated }
    }

    customKeyboardManager?.content()
}