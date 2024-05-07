package com.jkgug.example.storitest.repository.local.sesion

interface SessionRepository {

    /**
     * get the session state
     */
    suspend fun saveIsLogged(isLogged: Boolean)

    /**
     * Verify if the user is logged
     */
    fun isUserLogged(): Boolean

}
