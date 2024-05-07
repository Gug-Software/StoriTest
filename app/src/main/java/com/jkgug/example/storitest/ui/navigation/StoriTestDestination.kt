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

object MovementDetails : StoriTestDestination {
    override val route = "movementDetails"
    const val movementIdArg = "movementIdArg"
    val routeWithArgs = "${route}/{${movementIdArg}}"
    val arguments = listOf(
        navArgument(movementIdArg) { type = NavType.StringType }
    )
}