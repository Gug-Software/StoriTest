package com.jkgug.example.storitest.data.repository.logged

interface LoggedRepository {

    /**
     * Verify if the user is logged
     */
    fun isUserLogged(): Boolean


}
