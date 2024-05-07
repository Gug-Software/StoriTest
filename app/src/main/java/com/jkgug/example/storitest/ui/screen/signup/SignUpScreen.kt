package com.jkgug.example.storitest.ui.screen.signup

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
    onBackNavigation: () -> Unit,
    onSuccessNavigation: () -> Unit,
    viewModel: SignUpViewModel = koinViewModel(),
    snackBarHostState: SnackbarHostState,
) {

    val mediumPadding = dimensionResource(R.dimen.padding_m)
    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

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
            onSignInClick = onBackNavigation,
            isLoading = uiState.loading,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(bottomContent) {
                    centerHorizontallyTo(parent)
                    bottom.linkTo(parent.bottom)
                }
        )
    }

    if (uiState.navigateToSuccess) {
        LaunchedEffect(Unit) {
            scope.launch {
                onSuccessNavigation.invoke()
            }
        }
    }

    uiState.messageForUser?.let { userMessage ->
        LaunchedEffect(userMessage) {
            snackBarHostState.showSnackbar(userMessage)
            viewModel.snackBarMessageShown()

        }
    }

}

@Preview(showBackground = true)
@Composable
fun SignUpContentPreview() {
    StoriTestTheme {
        SignUpScreen(
            onBackNavigation = {},
            viewModel = koinViewModel(),
            snackBarHostState = SnackbarHostState(),
            onSuccessNavigation = { }
        )
    }
}