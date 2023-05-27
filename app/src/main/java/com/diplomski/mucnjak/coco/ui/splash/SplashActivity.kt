package com.diplomski.mucnjak.coco.ui.splash

import SAMSUNG_SM_X200
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.diplomski.mucnjak.coco.R
import com.diplomski.mucnjak.coco.ui.main.MainActivity
import com.diplomski.mucnjak.coco.ui.theme.CoCoTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.fetchConfiguration()
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        setContent {
            Content()
        }
    }
}

@Composable
private fun Content() {
    CoCoTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.surface,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                AnimatedLogo()
                Text(
                    text = "UČITAVANJE",
                    color = MaterialTheme.colors.secondary
                )
            }
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

@Composable
@Preview(device = SAMSUNG_SM_X200)
private fun Preview() {
    Content()
}

