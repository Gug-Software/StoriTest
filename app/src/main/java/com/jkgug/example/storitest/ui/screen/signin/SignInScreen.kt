package com.jkgug.example.storitest.ui.screen.signin

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jkgug.example.storitest.R
import com.jkgug.example.storitest.ui.components.signin.SignInBottomContent
import com.jkgug.example.storitest.ui.components.signin.SignInContent
import com.jkgug.example.storitest.ui.theme.StoriTestTheme
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    onSignInNavigation: () -> Unit,
    onHomeNavigation: () -> Unit,
    signInViewModel: SignInViewModel = viewModel()
) {

    val mediumPadding = dimensionResource(R.dimen.padding_m)
    val signInUiState by signInViewModel.uiState.collectAsState()

    if (signInUiState.navigateToHome) {
        onHomeNavigation.invoke()
    }

    if (signInUiState.errorInCredentials) {
        Toast.makeText(
            LocalContext.current,
            R.string.user_field_mail_placeholder,
            Toast.LENGTH_SHORT
        )
            .show()
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(mediumPadding)
    ) {
        val (topContent, bottomContent) = createRefs()
        SignInContent(
            onUserMailChanged = { signInViewModel.updateUserMail(it) },
            userMailValue = signInViewModel.userMail,
            isValidaMail = signInUiState.isValidEmail,
            onUserPasswordChanged = { signInViewModel.updateUserPassword(it) },
            userPasswordValue = signInViewModel.userPassword,
            enabledSignInButton = signInUiState.enabledSignInButton,
            onCheckSignIn = { signInViewModel.checkSignIn() },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(topContent) {
                    centerHorizontallyTo(parent)
                    top.linkTo(parent.top)
                    bottom.linkTo(bottomContent.top)
                }
        )
        SignInBottomContent(
            onSignInClick = onSignInNavigation,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(bottomContent) {
                    centerHorizontallyTo(parent)
                    bottom.linkTo(parent.bottom)
                }
        )
    }

}

@Preview(showBackground = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun SignInScreenPreview() {
    StoriTestTheme {
        SignInScreen(
            onSignInNavigation = { },
            onHomeNavigation = { }
        )
    }
}