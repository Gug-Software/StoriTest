package com.jkgug.example.storitest.ui.components.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jkgug.example.storitest.R
import com.jkgug.example.storitest.ui.theme.StoriTestTheme

@Composable
fun HomeTop(
    modifier: Modifier = Modifier,
    userName: String
) {

    val paddingXS = dimensionResource(R.dimen.padding_xs)
    val paddingM = dimensionResource(R.dimen.padding_m)
    val paddingL = dimensionResource(R.dimen.padding_l)
    val paddingXXL = dimensionResource(R.dimen.padding_xxl)

    Column(
        modifier = modifier.padding(
            top = paddingM, bottom = paddingL, start = paddingL, end = paddingL
        ),
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(paddingXXL),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(id = R.string.stori),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        Spacer(modifier = Modifier.height(paddingM))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = stringResource(id = R.string.home_hello),
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = userName.uppercase(),
                maxLines = 1,
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = paddingXS),
                overflow = TextOverflow.Ellipsis
            )
        }
        Text(
            text = stringResource(id = R.string.home_message),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.outline
        )
    }

}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "DefaultPreviewLight")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark")
@Composable
fun HomeTopPreview() {
    StoriTestTheme {
        HomeTop(
            modifier = Modifier.fillMaxWidth(),
            userName = "Juana Carlota"
        )
    }
}