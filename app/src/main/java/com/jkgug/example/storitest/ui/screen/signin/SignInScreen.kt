package com.jkgug.example.storitest.ui.screen.signin

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jkgug.example.storitest.R
import com.jkgug.example.storitest.ui.components.signin.SignInBottomContent
import com.jkgug.example.storitest.ui.components.signin.SignInContent
import com.jkgug.example.storitest.ui.theme.StoriTestTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInScreen(
    onSignUpNavigation: () -> Unit,
    onNavigateToHome: () -> Unit,
    viewModel: SignInViewModel = koinViewModel(),
    snackBarHostState: SnackbarHostState
) {

    val mediumPadding = dimensionResource(R.dimen.padding_m)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(mediumPadding)
    ) {
        val (topContent, bottomContent) = createRefs()
        SignInContent(
            onUserMailChanged = { viewModel.updateUserMail(it) },
            userMailValue = viewModel.userMail,
            isValidaMail = uiState.isValidEmail,
            onUserPasswordChanged = { viewModel.updateUserPassword(it) },
            userPasswordValue = viewModel.userPassword,
            enabledSignInButton = uiState.enabledSignInButton,
            onCheckSignIn = { viewModel.checkSignIn() },
            isLoading = uiState.loading,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(topContent) {
                    centerHorizontallyTo(parent)
                    top.linkTo(parent.top)
                    bottom.linkTo(bottomContent.top)
                }
        )
        SignInBottomContent(
            onSignInClick = onSignUpNavigation,
            isLoading = uiState.loading,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(bottomContent) {
                    centerHorizontallyTo(parent)
                    bottom.linkTo(parent.bottom)
                }
        )
    }

    if (uiState.navigateToHome) {
        LaunchedEffect(Unit) {
            scope.launch {
                onNavigateToHome.invoke()
            }
        }
    }

    uiState.messageForUser?.let { userMessage ->
        LaunchedEffect(userMessage) {
            scope.launch {
                snackBarHostState.showSnackbar(userMessage)
                viewModel.snackBarMessageShown()
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, name = "DefaultPreviewDark")
@Preview(uiMode = UI_MODE_NIGHT_NO, name = "DefaultPreviewLight")
@Composable
fun SignInScreenPreview() {
    StoriTestTheme {
        SignInScreen(
            onSignUpNavigation = { },
            onNavigateToHome = { },
            viewModel = koinViewModel(),
            snackBarHostState = SnackbarHostState()
        )
    }
}