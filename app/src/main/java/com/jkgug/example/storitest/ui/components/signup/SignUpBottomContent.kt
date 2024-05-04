package com.jkgug.example.storitest.ui.components.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jkgug.example.storitest.R
import com.jkgug.example.storitest.ui.theme.StoriTestTheme

@Composable
fun SignUpBottomContent(
    onSignInClick: () -> Unit,
    modifier: Modifier
) {

    val smallPadding = dimensionResource(R.dimen.padding_s)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(
            text = stringResource(R.string.signup_account),
        )

        Spacer(modifier = Modifier.width(smallPadding))

        TextButton(
            modifier = Modifier,
            onClick = onSignInClick
        ) {
            Text(
                text = stringResource(R.string.signup_signin),
                fontSize = 16.sp
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun SignUpBottomContentPreview() {
    StoriTestTheme {
        SignUpBottomContent(
            onSignInClick = {}, modifier = Modifier.padding(16.dp)
        )
    }
}