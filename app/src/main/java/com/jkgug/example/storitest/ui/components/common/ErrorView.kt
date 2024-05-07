package com.jkgug.example.storitest.ui.components.common

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.jkgug.example.storitest.R
import com.jkgug.example.storitest.ui.theme.StoriTestTheme

@Composable
fun ErrorView(
    onRetryAction: () -> Unit,
    modifier: Modifier
) {

    val paddingS = dimensionResource(R.dimen.padding_s)
    val paddingM = dimensionResource(R.dimen.padding_m)
    val paddingXL = dimensionResource(R.dimen.padding_xl)
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingXL),
        verticalArrangement = Arrangement.spacedBy(paddingS),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val cardElevation = dimensionResource(R.dimen.card_elevation)
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = cardElevation),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingM),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.error_title),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error
                )
                Text(
                    text = stringResource(id = R.string.error_subtitle),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        Button(
            modifier = Modifier,
            onClick = onRetryAction
        ) {
            Text(text = stringResource(id = R.string.error_retry))
        }
    }

}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, name = "DefaultPreviewDark")
@Preview(uiMode = UI_MODE_NIGHT_NO, name = "DefaultPreviewLight")
@Composable
fun ErrorViewPreview() {
    StoriTestTheme {
        ErrorView(
            onRetryAction = {},
            modifier = Modifier
                .fillMaxSize()
        )
    }
}