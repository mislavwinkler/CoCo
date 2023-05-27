package com.diplomski.mucnjak.coco.ui.split_screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.ui.split_screen.incorrect_solution.IncorrectSolutionScreen
import com.diplomski.mucnjak.coco.ui.split_screen.setup.SetupScreen
import com.diplomski.mucnjak.coco.ui.split_screen.solving.SolvingScreen
import com.diplomski.mucnjak.coco.ui.split_screen.studentinput.StudentInputScreen
import com.diplomski.mucnjak.coco.ui.split_screen.welcome.WelcomeScreen


abstract class Screen(private val routePath: String) {

    operator fun invoke() = routePath

    fun add(pathEnd: String) = "${this()}/$pathEnd"
}

sealed class Route {
    object StudentInputScreen : Screen("root")

    object SetupScreen : Screen(StudentInputScreen.add("setup"))

    object WelcomeScreen : Screen(SetupScreen.add("welcome"))

    object SolvingScreen : Screen(WelcomeScreen.add("solving"))

    object IncorrectSolutionScreen : Screen(SolvingScreen.add("incorrect_solution"))
}


@Composable
fun IndividualNavigationContent() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Route.StudentInputScreen()) {
        addStudentInputScreen(navController = navController)
        addSetupScreen(navController = navController)
        addWelcomeScreen(navController = navController)
        addSolvingScreen(navController = navController)
        addIncorrectSolutionScreen(navController = navController)
    }
}

private fun NavGraphBuilder.addStudentInputScreen(navController: NavController) {
    composable(route = Route.StudentInputScreen()) {
        StudentInputScreen {
            navController.navigate(route = Route.SetupScreen())
        }
    }
}

private fun NavGraphBuilder.addSetupScreen(navController: NavController) {
    composable(route = Route.SetupScreen()) {
        SetupScreen {
            navController.navigate(route = Route.WelcomeScreen())
        }
    }
}

private fun NavGraphBuilder.addWelcomeScreen(navController: NavController) {
    composable(route = Route.WelcomeScreen()) {
        WelcomeScreen {
            navController.navigate(route = Route.SolvingScreen())
        }
    }
}

private fun NavGraphBuilder.addSolvingScreen(navController: NavController) {
    composable(route = Route.SolvingScreen()) {
        SolvingScreen(navigateToIncorrectSolution = {
            navController.navigate(route = Route.IncorrectSolutionScreen())
        }, navigateToResults = { DoNothing })
    }
}

private fun NavGraphBuilder.addIncorrectSolutionScreen(navController: NavHostController) {
    composable(route = Route.IncorrectSolutionScreen()) {
        IncorrectSolutionScreen {
            throw NotImplementedError()
        }
    }
}
