package com.diplomski.mucnjak.coco.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.diplomski.mucnjak.coco.R
import com.diplomski.mucnjak.coco.ui.ComposeMock
import com.diplomski.mucnjak.coco.ui.theme.CoCoTheme
import com.diplomski.mucnjak.coco.ui.theme.Dimens

@Composable
private fun Branch(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.height(height = Dimens.branchHeight),
        painter = painterResource(id = R.drawable.branch),
        contentScale = ContentScale.FillHeight,
        contentDescription = null,
    )
}

@Composable
fun ReverseBranch(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.height(height = Dimens.branchHeight),
        painter = painterResource(id = R.drawable.branch_reverse),
        contentScale = ContentScale.FillHeight,
        contentDescription = null,
    )
}

@Composable
private fun Bird(modifier: Modifier = Modifier) {
    Image(
        modifier = Modifier
            .height(height = Dimens.birdHeight)
            .then(modifier),
        painter = painterResource(id = R.drawable.bird),
        contentScale = ContentScale.FillHeight,
        contentDescription = null,
    )
}

@Composable
private fun SadBird(modifier: Modifier = Modifier) {
    Image(
        modifier = Modifier
            .height(height = Dimens.birdHeight)
            .then(modifier),
        painter = painterResource(id = R.drawable.bird_sad),
        contentScale = ContentScale.FillHeight,
        contentDescription = null,
    )
}

@Composable
private fun BirdOnBranchTemplate(
    modifier: Modifier = Modifier,
    birdComposable: @Composable (birdModifier: Modifier) -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomStart
    ) {
        birdComposable(
            Modifier.padding(
                start = Dimens.birdOnBranchPaddingStart,
                bottom = Dimens.birdOnBranchPaddingBottom
            )
        )
        Branch(Modifier.offset(x = Dimens.birdOnBranchBranchOffset))
    }
}

@Composable
fun BirdOnBranch(modifier: Modifier = Modifier) {
    BirdOnBranchTemplate(modifier) { birdModifier ->
        Bird(birdModifier)
    }
}

@Composable
fun SadBirdOnBranch(modifier: Modifier = Modifier) {
    BirdOnBranchTemplate(modifier) { birdModifier ->
        SadBird(birdModifier)
    }
}

@Composable
@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
private fun PreviewBirdOnBranch() {
    CoCoTheme {
        BirdOnBranch()
    }
}

@Composable
@Preview(showSystemUi = true, device = ComposeMock.SAMSUNG_SM_X200)
private fun PreviewSadBirdOnBranch() {
    CoCoTheme {
        SadBirdOnBranch()
    }
}

@Composable
@Preview
private fun PreviewReverseBranch() {
    CoCoTheme {
        ReverseBranch()
    }
}
