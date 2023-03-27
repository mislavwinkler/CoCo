package com.diplomski.mucnjak.coco.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diplomski.mucnjak.coco.ui.theme.LocalCustomColor

@Composable
fun SplashScreen(navigateToNameInput: () -> Unit) {
    Content(navigateToNameInput)
}

@Composable
private fun Content(
    navigateToNameInput: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    Box(
        Modifier
            .background(color = LocalCustomColor.current.colorStudent1)
            .fillMaxSize()
            .clickable { navigateToNameInput() },
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Co|Co",
            style = MaterialTheme.typography.h1,
            color = Color.White
        )
    }
}
