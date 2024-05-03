package com.jkgug.example.storitest.ui.navigation

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
}