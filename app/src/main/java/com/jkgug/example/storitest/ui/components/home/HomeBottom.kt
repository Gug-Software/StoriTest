package com.jkgug.example.storitest.ui.components.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jkgug.example.storitest.R
import com.jkgug.example.storitest.data.BankMovement
import com.jkgug.example.storitest.ui.components.home.item.BankMovementItem
import com.jkgug.example.storitest.ui.theme.StoriTestTheme

@Composable
fun HomeBottom(
    movements: List<BankMovement> = emptyList(),
    modifier: Modifier = Modifier
) {

    val radiusL = dimensionResource(R.dimen.radius_l)
    val paddingXS = dimensionResource(R.dimen.padding_xs)
    val paddingS = dimensionResource(R.dimen.padding_s)
    val paddingM = dimensionResource(R.dimen.padding_m)
    val paddingL = dimensionResource(R.dimen.padding_l)
    val radiusXXL = dimensionResource(R.dimen.radius_xxl)

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
        ),
        shape = RoundedCornerShape(
            topStart = radiusXXL, topEnd = radiusL, bottomEnd = 0.dp, bottomStart = 0.dp
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = paddingL, end = paddingL, top = paddingL, bottom = 0.dp
                )

        ) {
            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .width(paddingL)
                        .height(paddingXS),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    )
                ) {
                    Spacer(
                        modifier = Modifier
                            .height(paddingXS)
                            .width(paddingL)
                    )
                }
            }
            Spacer(modifier = Modifier.height(paddingM))
            Text(
                text = stringResource(id = R.string.home_message),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary,
            )
            Spacer(modifier = Modifier.height(paddingM))
            LazyColumn(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(paddingS)
            ) {
                items(items = movements, itemContent = { item ->
                    BankMovementItem(bankMovement = item)
                })
            }
        }
    }

}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "DefaultPreviewLight")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark")
@Composable
fun HomeBottomPreview() {
    StoriTestTheme {
        HomeBottom(
            movements = listOf(
                BankMovement(
                    idTransaction = "voluptatum",
                    title = "cu",
                    date = "idque",
                    amount = "dico",
                    description = "te",
                    type = "sadipscing",
                    status = false
                ),
                BankMovement(
                    idTransaction = "voluptatum",
                    title = "cu",
                    date = "idque",
                    amount = "dico",
                    description = "",
                    type = "sadipscing",
                    status = false
                ),
                BankMovement(
                    idTransaction = "voluptatum",
                    title = "cu",
                    date = "idque",
                    amount = "dico",
                    description = "te",
                    type = "sadipscing",
                    status = false
                )
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}