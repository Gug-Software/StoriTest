package com.jkgug.example.storitest.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface StoriTestDestination {
    val route: String
}

object SignIn : StoriTestDestination {
    override val route = "signIn"
}

object SignUp : StoriTestDestination {
    override val route = "signUp"
}

object Home : StoriTestDestination {
    override val route = "home"
}

object Success : StoriTestDestination {
    override val route = "success"
}

object MovementDetails : StoriTestDestination {
    override val route = "movementDetails"
    const val MOVEMENT_ID_ARG = "movementIdArg"
    val routeWithArgs = "${route}/{${MOVEMENT_ID_ARG}}"
    val arguments = listOf(
        navArgument(MOVEMENT_ID_ARG) { type = NavType.StringType }
    )
}