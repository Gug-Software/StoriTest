package com.jkgug.example.storitest.ui.components.signup

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jkgug.example.storitest.R
import com.jkgug.example.storitest.ui.theme.StoriTestTheme

@Composable
fun SignUpBottomContent(
    onSignInClick: () -> Unit,
    isLoading: Boolean,
    modifier: Modifier,
) {
    val smallPadding = dimensionResource(R.dimen.padding_s)
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.signup_account),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.width(smallPadding))
        TextButton(
            modifier = Modifier,
            onClick = onSignInClick,
            enabled = isLoading.not()
        ) {
            Text(
                text = stringResource(R.string.signup_sign_in),
                style = MaterialTheme.typography.titleSmall
            )
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
fun SignUpBottomContentPreview() {
    StoriTestTheme {
        SignUpBottomContent(
            onSignInClick = {},
            isLoading = false,
            modifier = Modifier.padding(16.dp),
        )
    }
}