package com.jkgug.example.storitest.ui.components.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
fun SignUpContent(
    onSignInClick: () -> Unit,
    modifier: Modifier
) {

    val mediumPadding = dimensionResource(R.dimen.padding_m)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(mediumPadding),
    ) {
        Text(
            text = stringResource(R.string.signup_title),
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary
        )
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                Text(stringResource(R.string.user_field_name_hint))
            }
        )
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                Text(stringResource(R.string.user_field_lastname_hint))
            },
        )
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                Text(stringResource(R.string.user_field_mail_hint))
            }
        )
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                Text(stringResource(R.string.user_field_password_hint))
            },
        )
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd,
        ) {
            Button(
                modifier = Modifier,
                onClick = onSignInClick
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
@Composable
fun SignUpContentPreview() {
    StoriTestTheme {
        SignUpContent(
            onSignInClick = {}, modifier = Modifier.padding(16.dp)
        )
    }
}