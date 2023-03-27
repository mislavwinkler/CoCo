package com.diplomski.mucnjak.coco.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diplomski.mucnjak.coco.ui.splash.SplashScreen
import com.diplomski.mucnjak.coco.ui.studentinput.StudentInputScreen

sealed class Screen(val rootRoute: String) {

    object Splash: Screen("root")
    object StudentInput: Screen(rootRoute = "root/studentInput")
}

@Composable
fun NavigationContent() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.Splash.rootRoute) {
        addSplashScreen(navController)
        addStudentInputScreen(navController)
    }
}

private fun NavGraphBuilder.addSplashScreen(navController: NavController) {
    composable(route = Screen.Splash.rootRoute) {
        SplashScreen { navController.navigate(Screen.StudentInput.rootRoute) }
    }
}

private fun NavGraphBuilder.addStudentInputScreen(navController: NavController) {
    composable(route = Screen.StudentInput.rootRoute) {
        StudentInputScreen {

        }
    }
}