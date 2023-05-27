package com.diplomski.mucnjak.coco.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diplomski.mucnjak.coco.ui.home.HomeScreen
import com.diplomski.mucnjak.coco.ui.split_screen.SplitScreen

sealed class Screen(val rootRoute: String) {

    object Home: Screen("root")
    object SplitScreen: Screen(rootRoute = "root/studentInput")
}

@Composable
fun NavigationContent() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.Home.rootRoute) {
        addSplashScreen(navController)
        addSplitScreen(navController)
    }
}

private fun NavGraphBuilder.addSplashScreen(navController: NavController) {
    composable(route = Screen.Home.rootRoute) {
        HomeScreen { navController.navigate(Screen.SplitScreen.rootRoute) }
    }
}

private fun NavGraphBuilder.addSplitScreen(navController: NavController) {
    composable(route = Screen.SplitScreen.rootRoute) {
        SplitScreen()
    }
}