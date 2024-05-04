package com.jkgug.example.storitest.ui.components.signin

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jkgug.example.storitest.R
import com.jkgug.example.storitest.ui.components.common.MailField
import com.jkgug.example.storitest.ui.components.common.PasswordField
import com.jkgug.example.storitest.ui.theme.StoriTestTheme

@Composable
fun SignInContent(
    onUserMailChanged: (String) -> Unit,
    userMailValue: String,
    isValidaMail: Boolean,
    onUserPasswordChanged: (String) -> Unit,
    userPasswordValue: String,
    enabledSignInButton: Boolean,
    onCheckSignIn: () -> Unit,
    modifier: Modifier,
) {

    val xlPadding = dimensionResource(R.dimen.padding_xl)
    val mediumPadding = dimensionResource(R.dimen.padding_m)
    val cardElevation = dimensionResource(R.dimen.card_elevation)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.sigin_title),
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = stringResource(R.string.sigin_description),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(xlPadding))
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = cardElevation),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
        ) {
            Column(
                modifier = Modifier.padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MailField(
                    onUserMailChanged = onUserMailChanged,
                    userMailValue = userMailValue,
                    isValidMail = isValidaMail,
                    modifier = Modifier.fillMaxWidth()
                )
                PasswordField(
                    onUserPasswordChanged = onUserPasswordChanged,
                    userPasswordValue = userPasswordValue,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
        Spacer(modifier = Modifier.height(xlPadding))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd,
        ) {
            Button(
                modifier = Modifier,
                enabled = enabledSignInButton,
                onClick = onCheckSignIn
            ) {
                Text(
                    text = stringResource(R.string.sigin_button),
                    fontSize = 16.sp
                )
            }
        }

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
fun SignInContentPreview() {
    StoriTestTheme {
        SignInContent(
            onUserMailChanged = {},
            userMailValue = "alo",
            isValidaMail = true,
            onUserPasswordChanged = {},
            userPasswordValue = "algo",
            enabledSignInButton = true,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            onCheckSignIn = { }

        )
    }
}