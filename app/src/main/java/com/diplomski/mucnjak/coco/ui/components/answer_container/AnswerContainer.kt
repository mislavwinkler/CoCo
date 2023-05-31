package com.diplomski.mucnjak.coco.ui.components.answer_container

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.diplomski.mucnjak.coco.R
import com.diplomski.mucnjak.coco.data.ui.Answer
import com.diplomski.mucnjak.coco.data.ui.AnswerType
import com.diplomski.mucnjak.coco.ui.ComposeMock
import com.diplomski.mucnjak.coco.ui.theme.CoCoTheme
import com.diplomski.mucnjak.coco.ui.theme.Dimens

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnswerContainer(
    modifier: Modifier = Modifier,
    answers: List<Answer>,
    onAnswerClick: (Answer) -> Unit,
    customButtonColor: Color = MaterialTheme.colors.surface
) {
    Surface(
        modifier = modifier.verticalScroll(rememberScrollState()),
        color = MaterialTheme.colors.secondary.copy(alpha = 0.3f),
        shape = MaterialTheme.shapes.small
    ) {
        FlowRow(
            modifier = Modifier.padding(end = Dimens.x2, bottom = Dimens.x2),
            maxItemsInEachRow = if (answers.any { it.type == AnswerType.IMAGE }) 3 else Int.MAX_VALUE,
        ) {
            for (answer in answers) {
                if (answer.type == AnswerType.IMAGE) {
                    ImageAnswer(
                        imageUrl = answer.value,
                        onClick = { onAnswerClick(answer) },
                        isSelected = answer.incorrect
                    )
                } else {
                    TextAnswer(
                        customButtonColor = customButtonColor,
                        text = answer.value,
                        onClick = { onAnswerClick(answer) },
                        isSelected = answer.incorrect
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TextAnswer(
    text: String,
    onClick: () ->
    Unit, isSelected: Boolean,
    customButtonColor: Color = MaterialTheme.colors.surface
) {
    FilterChip(
        modifier = Modifier.padding(start = Dimens.answerPadding, top = Dimens.answerPadding),
        selected = isSelected,
        onClick = onClick,
        colors = ChipDefaults.filterChipColors(
            backgroundColor = customButtonColor,
            contentColor = MaterialTheme.colors.primary,
            selectedBackgroundColor = MaterialTheme.colors.error,
            selectedContentColor = MaterialTheme.colors.onError
        ),
        shape = MaterialTheme.shapes.small,
    ) {
        Text(
            modifier = Modifier.padding(horizontal = Dimens.x0_5),
            text = text.uppercase(),
            style = MaterialTheme.typography.subtitle2,
        )
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun ImageAnswer(imageUrl: String, onClick: () -> Unit, isSelected: Boolean) {
    Card(
        modifier = Modifier
            .sizeIn(minWidth = Dimens.minImageAnswerSize)
            .fillMaxWidth(0.3f)
            .aspectRatio(1f)
            .padding(start = Dimens.answerPadding, top = Dimens.answerPadding),
        onClick = onClick,
        border = if (isSelected) BorderStroke(Dimens.x0_5, MaterialTheme.colors.error) else null,
        shape = MaterialTheme.shapes.small,
    ) {
        GlideImage(
            modifier = Modifier
                .fillMaxSize(),
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        ) {
            it.placeholder(R.drawable.ic_logo_large)
                .error(R.drawable.ic_logo_large)
        }
    }
}

@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
@Composable
fun PreviewTextAnswer() {
    CoCoTheme {
        Column {
            TextAnswer(text = ComposeMock.LOREM_IPSUM, onClick = {}, isSelected = false)
            TextAnswer(text = ComposeMock.LOREM_IPSUM, onClick = {}, isSelected = true)
        }
    }
}

@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
@Composable
fun PreviewImageAnswer() {
    CoCoTheme {
        Column {
            ImageAnswer(imageUrl = ComposeMock.IMAGE_URL, onClick = {}, isSelected = false)
            ImageAnswer(imageUrl = ComposeMock.IMAGE_URL, onClick = {}, isSelected = true)
        }
    }
}

@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
@Composable
fun PreviewAnswerContainer() {
    CoCoTheme {
        AnswerContainer(answers = ComposeMock.buildAnswersList(), onAnswerClick = {})
    }
}

@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
@Composable
fun PreviewAnswerContainerImages() {
    CoCoTheme {
        AnswerContainer(
            answers = ComposeMock.buildAnswersList(), onAnswerClick = {})
    }
}
