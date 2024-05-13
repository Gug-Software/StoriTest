package com.jkgug.example.storitest.ui.screen.signin

import com.jkgug.example.storitest.MainCoroutineRule
import com.jkgug.example.storitest.usecase.SaveUserDataLocallyUseCase
import com.jkgug.example.storitest.usecase.SignInUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SignInViewModelTest {

    @Mock
    private lateinit var signInUseCase: SignInUseCase


    @Mock
    private lateinit var saveUserUseCase: SaveUserDataLocallyUseCase

    private lateinit var viewModel: SignInViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined) // Set the Main dispatcher for tests
        MockitoAnnotations.openMocks(this)
        viewModel = SignInViewModel(signInUseCase, saveUserUseCase)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun checkSignIn(): Unit = runTest {

        Dispatchers.setMain(StandardTestDispatcher())

        val mail = "some"
        val password = "some"
        val firebaseUserId = "firebaseUserId"

//        Mockito.`when`(signInUseCase(mail, password)).thenReturn(
//            flow { NetworkResult.Success(firebaseUserId) }
//        )

        assert(viewModel.uiState.value.loading)

        viewModel.signIn()

        //verify(signInUseCase).invoke(mail, password)
    }

}