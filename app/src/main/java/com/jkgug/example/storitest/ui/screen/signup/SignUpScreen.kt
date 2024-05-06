package com.jkgug.example.storitest.ui.screen.signup

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.jkgug.example.storitest.R
import com.jkgug.example.storitest.ui.components.signup.SignUpBottomContent
import com.jkgug.example.storitest.ui.components.signup.SignUpContent
import com.jkgug.example.storitest.ui.theme.StoriTestTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreen(
    onHomeNavigation: () -> Unit,
    viewModel: SignUpViewModel = koinViewModel(),
) {

    val snackBarHostState = remember { SnackbarHostState() }
    val mediumPadding = dimensionResource(R.dimen.padding_m)
    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
    ) { _ ->

        if (uiState.navigateToHome) {
            LaunchedEffect(Unit) {
                scope.launch {
                    onHomeNavigation.invoke()
                }
            }
        }

        uiState.messageForUser?.let { userMessage ->
            LaunchedEffect(userMessage) {
                snackBarHostState.showSnackbar(userMessage)
                viewModel.snackBarMessageShown()

            }
        }

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(mediumPadding)
        ) {
            val (topContent, bottomContent) = createRefs()
            SignUpContent(
                onUserNameChanged = { viewModel.updateUserName(it) },
                userNameValue = viewModel.userName,
                onUserLastNameChanged = { viewModel.updateUserLastName(it) },
                userLastNameValue = viewModel.userLastName,
                onUserMailChanged = { viewModel.updateUserMail(it) },
                userMailValue = viewModel.userMail,
                onUserPasswordChanged = { viewModel.updateUserPassword(it) },
                userPasswordValue = viewModel.userPassword,
                isValidaMail = uiState.isValidEmail,
                enabledSignInButton = uiState.enabledSignInButton,
                onCheckSignUp = { viewModel.checkSignUp() },
                isLoading = uiState.loading,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(topContent) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                        bottom.linkTo(bottomContent.top)
                    }
            )
            SignUpBottomContent(
                onSignInClick = {},
                isLoading = uiState.loading,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(bottomContent) {
                        centerHorizontallyTo(parent)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
    }


}

@Preview(showBackground = true)
@Composable
fun SignUpContentPreview() {
    StoriTestTheme {
        SignUpScreen(
            onHomeNavigation = {},
            viewModel = SignUpViewModel(koinViewModel())
        )
    }
}