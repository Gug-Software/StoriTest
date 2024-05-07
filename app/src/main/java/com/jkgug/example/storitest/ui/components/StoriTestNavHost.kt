package com.jkgug.example.storitest.ui.components

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import com.jkgug.example.storitest.ui.navigation.Success
import com.jkgug.example.storitest.ui.screen.home.HomeScreen
import com.jkgug.example.storitest.ui.screen.details.MovementDetailsScreen
import com.jkgug.example.storitest.ui.screen.navhost.NavHostViewModel
import com.jkgug.example.storitest.ui.screen.signin.SignInScreen
import com.jkgug.example.storitest.ui.screen.signup.SignUpScreen
import com.jkgug.example.storitest.ui.screen.success.SuccessScreen
import com.jkgug.example.storitest.ui.theme.StoriTestTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun StoriTestNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState,
    viewModel: NavHostViewModel = koinViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    uiState.isUserLogged?.let { isUserLogged ->
        val startDestination = getStartDestination(isUserLogged)
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier
        ) {

            composable(
                route = SignIn.route,
            ) {
                SignInScreen(
                    onSignUpNavigation = { navController.navigateSingleTopTo(SignUp.route) },
                    onNavigateToHome = {
                        navController.navigate(Home.route) {
                            popUpToTop(navController)
                        }
                    },
                    snackBarHostState = snackBarHostState
                )
            }

            composable(
                route = SignUp.route,
            ) {
                SignUpScreen(
                    onBackNavigation = { navController.popBackStack() },
                    onSuccessNavigation = {
                        navController.navigate(Success.route) { popUpToTop(navController) }
                    },
                    snackBarHostState = snackBarHostState
                )
            }

            composable(
                route = Success.route
            ) {
                SuccessScreen(
                    onSuccessEndNavigation = {
                        navController.navigateSingleTopTo(SignIn.route)
                    },
                )
            }

            composable(
                route = Home.route
            ) {
                HomeScreen(
                    onMovementClick = { movementId ->
                        navController.navigateToMovementDetails(movementId)
                    },
                    onLogoutNavigation = {
                        navController.navigate(SignIn.route) { popUpToTop(navController) }
                    },
                )
            }

            composable(
                route = MovementDetails.routeWithArgs,
                arguments = MovementDetails.arguments,
            ) { _ ->
                MovementDetailsScreen(
                    onBackNavigation = { navController.popBackStack() },
                )
            }
        }
    }

}

@Composable
private fun getStartDestination(isUserLogged: Boolean) = if (isUserLogged) {
    Home.route
} else {
    SignIn.route
}

@Preview(showBackground = true)
@Composable
fun StoriTestNavHostPreview() {
    StoriTestTheme { StoriTestApp() }
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

private fun NavHostController.navigateToMovementDetails(movementId: String) {
    this.navigateSingleTopTo("${MovementDetails.route}/$movementId")
}
