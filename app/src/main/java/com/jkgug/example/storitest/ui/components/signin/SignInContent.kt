package com.jkgug.example.storitest.ui.components.signin

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jkgug.example.storitest.R
import com.jkgug.example.storitest.ui.components.common.MailField
import com.jkgug.example.storitest.ui.components.common.PasswordField
import com.jkgug.example.storitest.ui.theme.StoriTestTheme
import com.jkgug.example.storitest.utils.OnFieldValueChanged
import com.jkgug.example.storitest.utils.SubmitAction

@Composable
fun SignInContent(
    onUserMailChanged: OnFieldValueChanged,
    userMailValue: String,
    isValidaMail: Boolean,
    onUserPasswordChanged: OnFieldValueChanged,
    userPasswordValue: String,
    enabledSignInButton: Boolean,
    onSignIn: SubmitAction,
    modifier: Modifier,
    isLoading: Boolean,
) {

    val paddingS = dimensionResource(R.dimen.padding_s)
    val mediumPadding = dimensionResource(R.dimen.padding_m)
    val xlPadding = dimensionResource(R.dimen.padding_xl)
    val cardElevation = dimensionResource(R.dimen.card_elevation)
    val fieldHeight = dimensionResource(R.dimen.field_height)

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
                modifier = Modifier.padding(vertical = paddingS),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MailField(
                    onUserMailChanged = onUserMailChanged,
                    userMailValue = userMailValue,
                    isValidMail = isValidaMail,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = isLoading.not(),
                )
                PasswordField(
                    onUserPasswordChanged = onUserPasswordChanged,
                    userPasswordValue = userPasswordValue,
                    onKeyboardDoneActions = onSignIn,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = isLoading.not(),
                )
            }
        }
        Spacer(modifier = Modifier.height(xlPadding))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(fieldHeight),
            contentAlignment = Alignment.CenterEnd,
        ) {
            if (isLoading) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = mediumPadding)
                )
            } else {
                Button(
                    modifier = Modifier,
                    enabled = enabledSignInButton,
                    onClick = onSignIn
                ) {
                    Text(
                        text = stringResource(R.string.sigin_button),
                    )
                }
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
            onSignIn = { },
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            isLoading = false

        )
    }
}