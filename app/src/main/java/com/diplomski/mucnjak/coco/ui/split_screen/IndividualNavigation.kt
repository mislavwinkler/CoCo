package com.diplomski.mucnjak.coco.ui.split_screen

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.ui.split_screen.discussion.DiscussionScreen
import com.diplomski.mucnjak.coco.ui.split_screen.finish_note.FinishNoteScreen
import com.diplomski.mucnjak.coco.ui.split_screen.incorrect_solution.IncorrectSolutionScreen
import com.diplomski.mucnjak.coco.ui.split_screen.retry_note.RetryNoteScreen
import com.diplomski.mucnjak.coco.ui.split_screen.setup.SetupScreen
import com.diplomski.mucnjak.coco.ui.split_screen.solving.SolvingScreen
import com.diplomski.mucnjak.coco.ui.split_screen.studentinput.StudentInputScreen
import com.diplomski.mucnjak.coco.ui.split_screen.welcome.WelcomeScreen


abstract class Screen(private val routePath: String) {

    operator fun invoke(): String = routePath

    fun add(pathEnd: String): String = "${this()}/$pathEnd"
}

sealed class Route {
    object StudentInputScreen : Screen(routePath = "root")

    object SetupScreen : Screen(StudentInputScreen.add(pathEnd = "setup"))

    object WelcomeScreen : Screen(SetupScreen.add(pathEnd = "welcome"))

    object SolvingScreen : Screen(WelcomeScreen.add(pathEnd = "solving"))

    object IncorrectSolutionScreen : Screen(SolvingScreen.add(pathEnd = "incorrect_solution"))

    object DiscussionScreen : Screen(IncorrectSolutionScreen.add(pathEnd = "discussion"))

    object RetryNoteScreen : Screen(DiscussionScreen.add(pathEnd = "retry_note"))

    object FinishNoteScreen : Screen(SolvingScreen.add(pathEnd = "finish_note"))
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
        addDiscussionScreen(navController = navController)
        addRetryNoteScreen(navController = navController)
        addFinishNoteScreen()
    }
}

private fun NavGraphBuilder.addStudentInputScreen(navController: NavController) {
    composable(route = Route.StudentInputScreen()) {
        BackHandler(enabled = true) { DoNothing }
        StudentInputScreen {
            navController.navigate(route = Route.SetupScreen())
        }
    }
}

private fun NavGraphBuilder.addSetupScreen(navController: NavController) {
    composable(route = Route.SetupScreen()) {
        BackHandler(enabled = true) { DoNothing }
        SetupScreen {
            navController.navigate(route = Route.WelcomeScreen())
        }
    }
}

private fun NavGraphBuilder.addWelcomeScreen(navController: NavController) {
    composable(route = Route.WelcomeScreen()) {
        BackHandler(enabled = true) { DoNothing }
        WelcomeScreen {
            navController.navigate(route = Route.SolvingScreen())
        }
    }
}

private fun NavGraphBuilder.addSolvingScreen(navController: NavController) {
    composable(route = Route.SolvingScreen()) {
        BackHandler(enabled = true) { DoNothing }
        SolvingScreen(navigateToIncorrectSolution = {
            navController.navigate(route = Route.IncorrectSolutionScreen())
        }, navigateToFinishNote = {
            navController.navigate(route = Route.FinishNoteScreen())
        })
    }
}

private fun NavGraphBuilder.addIncorrectSolutionScreen(navController: NavHostController) {
    composable(route = Route.IncorrectSolutionScreen()) {
        BackHandler(enabled = true) { DoNothing }
        IncorrectSolutionScreen {
            navController.navigate(route = Route.DiscussionScreen())
        }
    }
}

private fun NavGraphBuilder.addDiscussionScreen(navController: NavController) {
    composable(route = Route.DiscussionScreen()) {
        BackHandler(enabled = true) { DoNothing }
        DiscussionScreen {
            navController.navigate(route = Route.RetryNoteScreen())
        }
    }
}

private fun NavGraphBuilder.addRetryNoteScreen(navController: NavController) {
    composable(route = Route.RetryNoteScreen()) {
        BackHandler(enabled = true) { DoNothing }
        RetryNoteScreen {
            navController.popBackStack(route = Route.SolvingScreen(), inclusive = true)
            navController.navigate(route = Route.SolvingScreen())
        }
    }
}

private fun NavGraphBuilder.addFinishNoteScreen() {
    composable(route = Route.FinishNoteScreen()) {
        BackHandler(enabled = true) { DoNothing }
        FinishNoteScreen()
    }
}
