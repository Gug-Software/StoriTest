package com.jkgug.example.storitest.ui.screen.signup

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jkgug.example.storitest.R
import com.jkgug.example.storitest.ui.components.signup.SignUpBottomContent
import com.jkgug.example.storitest.ui.components.signup.SignUpContent
import com.jkgug.example.storitest.ui.theme.StoriTestTheme

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = viewModel()
) {

    val mediumPadding = dimensionResource(R.dimen.padding_m)
    val uiState by viewModel.uiState.collectAsState()

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
            isValidaMail = uiState.isValidEmail,
            onUserPasswordChanged = { viewModel.updateUserPassword(it) },
            userPasswordValue = viewModel.userPassword,
            enabledSignInButton = uiState.enabledSignInButton,
            onCheckSignUp = { viewModel.checkSignUp() },
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
@Composable
fun SignUpContentPreview() {
    StoriTestTheme {
        SignUpScreen(
            viewModel = SignUpViewModel()
        )
    }
}