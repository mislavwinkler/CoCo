package com.diplomski.mucnjak.coco.ui.main

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.ui.home.HomeScreen
import com.diplomski.mucnjak.coco.ui.solutions.SolutionsScreen
import com.diplomski.mucnjak.coco.ui.split_screen.SplitScreen

abstract class Screen(private val routePath: String) {

    operator fun invoke() = routePath

    fun add(pathEnd: String) = "${this()}/$pathEnd"
}

sealed class Route {

    object Home : Screen(routePath = "root")
    object SplitScreen : Screen(routePath = Home.add(pathEnd = "student_input"))
    object SolutionsScreen : Screen(routePath = SplitScreen.add(pathEnd = "solutions"))
}

@Composable
fun NavigationContent() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Route.Home()) {
        addSplashScreen(navController = navController)
        addSplitScreen(navController = navController)
        addSolutionsScreen(navController = navController)
    }
}

private fun NavGraphBuilder.addSplashScreen(navController: NavController) {
    composable(route = Route.Home()) {
        HomeScreen { navController.navigate(route = Route.SplitScreen()) }
    }
}

private fun NavGraphBuilder.addSplitScreen(navController: NavController) {
    composable(route = Route.SplitScreen()) {
        BackHandler(enabled = true) { DoNothing }
        SplitScreen { navController.navigate(route = Route.SolutionsScreen()) }
    }
}

private fun NavGraphBuilder.addSolutionsScreen(navController: NavController) {
    composable(route = Route.SolutionsScreen()) {
        BackHandler(enabled = true) { DoNothing }
        SolutionsScreen {
            navController.popBackStack(route = Route.Home(), inclusive = false)
        }
    }
}
