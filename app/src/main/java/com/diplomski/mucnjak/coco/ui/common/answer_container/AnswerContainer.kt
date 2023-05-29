package com.diplomski.mucnjak.coco.ui.common.answer_container

import com.diplomski.mucnjak.coco.ui.common.SAMSUNG_SM_X200
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.diplomski.mucnjak.coco.R
import com.diplomski.mucnjak.coco.data.ui.Answer
import com.diplomski.mucnjak.coco.data.ui.AnswerType
import com.diplomski.mucnjak.coco.ui.theme.CoCoTheme
import kotlin.random.Random

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnswerContainer(
    modifier: Modifier = Modifier,
    answers: List<Answer>,
    onAnswerClick: (Answer) -> Unit,
) {
    Surface(
        modifier = modifier.verticalScroll(rememberScrollState()),
        color = MaterialTheme.colors.secondary.copy(alpha = 0.3f),
        shape = RoundedCornerShape(3.333.dp)
    ) {
        FlowRow(
            modifier = Modifier.padding(end = 13.333.dp, bottom = 13.333.dp),
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
fun TextAnswer(text: String, onClick: () -> Unit, isSelected: Boolean) {
    FilterChip(
        modifier = Modifier.padding(start = 10.dp, top = 10.dp),
        selected = isSelected,
        onClick = onClick,
        colors = ChipDefaults.filterChipColors(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.primary,
            selectedBackgroundColor = MaterialTheme.colors.error,
            selectedContentColor = MaterialTheme.colors.onError
        ),
        shape = RoundedCornerShape(6.667.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 3.333.dp),
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
            .fillMaxWidth(0.3f)
            .aspectRatio(1f)
            .padding(start = 10.dp, top = 10.dp),
        onClick = onClick,
        border = if (isSelected) BorderStroke(4.dp, MaterialTheme.colors.error) else null,
        shape = RoundedCornerShape(6.667.dp)
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

@Preview(device = SAMSUNG_SM_X200)
@Composable
fun PreviewTextAnswer() {
    CoCoTheme {
        Column {
            TextAnswer(text = "text", onClick = {}, isSelected = false)
            TextAnswer(text = "text", onClick = {}, isSelected = true)
        }
    }
}

@Preview(device = SAMSUNG_SM_X200)
@Composable
fun PreviewImageAnswer() {
    CoCoTheme {
        Column {
            ImageAnswer(imageUrl = "URL", onClick = {}, isSelected = false)
            ImageAnswer(imageUrl = "URL", onClick = {}, isSelected = true)
        }
    }
}

@Preview(device = SAMSUNG_SM_X200)
@Composable
fun PreviewAnswerContainer() {
    CoCoTheme {
        AnswerContainer(answers = buildList {
            for (i in 1..5) {
                add(
                    Answer(
                        "Random answer",
                        type = AnswerType.TEXT
                    )
                )
                add(
                    Answer(
                        "Ans",
                        type = AnswerType.TEXT,
                        incorrect = Random.Default.nextBoolean()
                    )
                )
            }
        }.shuffled(), onAnswerClick = {})
    }
}

@Preview(device = SAMSUNG_SM_X200)
@Composable
fun PreviewAnswerContainerImages() {
    CoCoTheme {
        AnswerContainer(
            answers = buildList {
            for (i in 1..10) {
                add(
                    Answer(
                        "URL",
                        type = AnswerType.IMAGE,
                        incorrect = Random.Default.nextBoolean()
                    )
                )
            }
        }.shuffled(), onAnswerClick = {})
    }
}
