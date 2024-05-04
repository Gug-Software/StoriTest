package com.jkgug.example.storitest.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jkgug.example.storitest.R
import com.jkgug.example.storitest.ui.components.signin.SignInBottomContent
import com.jkgug.example.storitest.ui.components.signin.SignInContent
import com.jkgug.example.storitest.ui.components.signup.SignUpBottomContent
import com.jkgug.example.storitest.ui.components.signup.SignUpContent
import com.jkgug.example.storitest.ui.theme.StoriTestTheme

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {

    val mediumPadding = dimensionResource(R.dimen.padding_m)

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(mediumPadding)
    ) {
        val (topContent, bottomContent) = createRefs()
        SignUpContent(
            onSignInClick = { },
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
            modifier = Modifier.padding(16.dp)
        )
    }
}