package com.jkgug.example.storitest.ui.components.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jkgug.example.storitest.R
import com.jkgug.example.storitest.data.BankMovement
import com.jkgug.example.storitest.ui.theme.StoriTestTheme

@Composable
fun DetailsTop(
    bankMovement: BankMovement,
    modifier: Modifier = Modifier
) {

    val paddingXS = dimensionResource(R.dimen.padding_xs)
    val paddingM = dimensionResource(R.dimen.padding_m)
    val paddingL = dimensionResource(R.dimen.padding_l)
    val paddingXXL = dimensionResource(R.dimen.padding_xxl)
    val paddingS = dimensionResource(R.dimen.padding_s)
    val cardElevation = dimensionResource(R.dimen.card_elevation)
    val radiusM = dimensionResource(R.dimen.radius_m)
    val sizeImage = dimensionResource(R.dimen.size_image_item_movement)

    Column(
        modifier = modifier.padding(
            top = paddingM, bottom = paddingL, start = paddingL, end = paddingL
        ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(paddingS)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Card(
                modifier = Modifier
                    .size(sizeImage)
                    .padding(paddingS),
                elevation = CardDefaults.cardElevation(defaultElevation = cardElevation),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
                shape = RoundedCornerShape(radiusM),
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "Icon"
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
            ) {
                Text(
                    text = bankMovement.title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = bankMovement.date,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Card(
            modifier = Modifier,
            elevation = CardDefaults.cardElevation(defaultElevation = cardElevation),
            colors = CardDefaults.cardColors(
                containerColor = if (bankMovement.status) {
                    Color.Green
                } else {
                    MaterialTheme.colorScheme.errorContainer
                },
            ),
            shape = RoundedCornerShape(radiusM),
        ) {
            Text(
                text = if (bankMovement.status) {
                    stringResource(id = R.string.details_approved)
                } else {
                    stringResource(id = R.string.details_cancelled)
                },
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(paddingS)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = R.string.details_total_amount),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = bankMovement.amount,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

    }

}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "DefaultPreviewLight")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark")
@Composable
fun HomeTopPreview() {
    StoriTestTheme {
        DetailsTop(
            bankMovement = BankMovement(
                idTransaction = "malesuada",
                title = "sententiae",
                date = "et",
                amount = "delectus",
                description = "efficitur",
                type = "ullamcorper",
                status = false
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}