package com.jkgug.example.storitest.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jkgug.example.storitest.StoriTestApp
import com.jkgug.example.storitest.ui.navigation.Home
import com.jkgug.example.storitest.ui.navigation.MovementDetails
import com.jkgug.example.storitest.ui.navigation.SignIn
import com.jkgug.example.storitest.ui.navigation.SignUp
import com.jkgug.example.storitest.ui.screen.HomeScreen
import com.jkgug.example.storitest.ui.screen.MovementDetailsScreen
import com.jkgug.example.storitest.ui.screen.signin.SignInScreen
import com.jkgug.example.storitest.ui.screen.signup.SignUpScreen
import com.jkgug.example.storitest.ui.theme.StoriTestTheme

@Composable
fun StoriTestNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = SignIn.route,
        modifier = modifier
    ) {

        composable(
            route = SignIn.route,
        ) {
            SignInScreen(
                onSignInNavigation = {
                    navController.navigateSingleTopTo(SignUp.route)
                },
                onHomeNavigation = {
                    //navController.navigateSingleTopTo(Home.route)
                    navController.navigate(Home.route) {
                        popUpToTop(navController)
                    }
                }
            )
        }

        composable(
            route = SignUp.route,
        ) {
            SignUpScreen()
        }

        composable(
            route = Home.route
        ) {
            HomeScreen()
        }

        composable(
            route = MovementDetails.route
        ) {
            MovementDetailsScreen()
        }

    }
}

@Preview(showBackground = true)
@Composable
fun StoriTestNavHostPreview() {
    StoriTestTheme {
        StoriTestApp("Android")
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        inclusive = true
    }
}
