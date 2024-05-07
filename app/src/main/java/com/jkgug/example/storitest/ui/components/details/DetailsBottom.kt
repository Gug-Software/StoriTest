package com.jkgug.example.storitest.ui.components.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jkgug.example.storitest.R
import com.jkgug.example.storitest.domain.BankMovement
import com.jkgug.example.storitest.ui.theme.StoriTestTheme

@Composable
fun DetailsBottom(
    bankMovement: BankMovement,
    modifier: Modifier = Modifier
) {

    val paddingM = dimensionResource(R.dimen.padding_m)
    val paddingS = dimensionResource(R.dimen.padding_s)

    Card(
        modifier = modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
        ),
        shape = RectangleShape,
    ) {
        Column(
            modifier = Modifier.padding(paddingM),
            verticalArrangement = Arrangement.spacedBy(paddingS),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingS),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(id = R.string.details_transaction_type),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = bankMovement.type,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingS),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(id = R.string.details_transaction_number),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = bankMovement.idTransaction,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }


    }


}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "DefaultPreviewLight")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark")
@Composable
fun DetailsBottomPreview() {
    StoriTestTheme {
        DetailsBottom(
            bankMovement = BankMovement(
                idTransaction = "hinc",
                title = "pertinax",
                date = "similique",
                amount = "mandamus",
                description = "atomorum",
                type = "sociis",
                status = false
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}