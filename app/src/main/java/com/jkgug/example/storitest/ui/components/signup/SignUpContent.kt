package com.jkgug.example.storitest.ui.components.signup

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.jkgug.example.storitest.ui.components.common.CommonFormField
import com.jkgug.example.storitest.ui.components.common.MailField
import com.jkgug.example.storitest.ui.components.common.PasswordField
import com.jkgug.example.storitest.ui.theme.StoriTestTheme

@Composable
fun SignUpContent(
    onUserNameChanged: (String) -> Unit,
    userNameValue: String,
    onUserLastNameChanged: (String) -> Unit,
    userLastNameValue: String,
    onUserMailChanged: (String) -> Unit,
    userMailValue: String,
    isValidaMail: Boolean,
    onUserPasswordChanged: (String) -> Unit,
    userPasswordValue: String,
    enabledSignInButton: Boolean,
    onCheckSignUp: () -> Unit,
    modifier: Modifier,
    isLoading: Boolean
) {

    val mediumPadding = dimensionResource(R.dimen.padding_m)
    val xlPadding = dimensionResource(R.dimen.padding_xl)
    val cardElevation = dimensionResource(R.dimen.card_elevation)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(mediumPadding),
    ) {
        Text(
            text = stringResource(R.string.signup_title),
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary
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
                CommonFormField(
                    placeHolderString = stringResource(R.string.user_field_name_hint),
                    onValueChanged = onUserNameChanged,
                    value = userNameValue,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = isLoading.not(),
                )
                CommonFormField(
                    placeHolderString = stringResource(R.string.user_field_lastname_hint),
                    onValueChanged = onUserLastNameChanged,
                    value = userLastNameValue,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = isLoading.not(),
                )
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
                    onKeyboardDoneActions = onCheckSignUp,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = isLoading.not(),
                )
            }
        }

        Spacer(modifier = Modifier.height(xlPadding))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(mediumPadding),
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
                    modifier = Modifier.padding(start = mediumPadding),
                    enabled = enabledSignInButton,
                    onClick = onCheckSignUp
                ) {
                    Text(
                        text = stringResource(R.string.signup_button),
                        style = MaterialTheme.typography.bodySmall,
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
fun SignUpContentPreview() {
    StoriTestTheme {
        SignUpContent(
            onUserNameChanged = {},
            userNameValue = "Marvin Price",
            onUserLastNameChanged = {},
            userLastNameValue = "Nikki Tyler",
            onUserMailChanged = {},
            userMailValue = "homero",
            isValidaMail = false,
            onUserPasswordChanged = {},
            userPasswordValue = "pericula",
            enabledSignInButton = false,
            onCheckSignUp = {},
            modifier = Modifier.fillMaxWidth(),
            isLoading = true,
        )
    }
}