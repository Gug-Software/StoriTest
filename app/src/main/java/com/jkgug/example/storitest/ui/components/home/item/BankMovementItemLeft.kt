package com.jkgug.example.storitest.ui.components.home.item

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jkgug.example.storitest.R
import com.jkgug.example.storitest.data.BankMovement
import com.jkgug.example.storitest.ui.theme.StoriTestTheme

@Composable
fun BankMovementItemLeft(
    modifier: Modifier = Modifier,
    bankMovement: BankMovement
) {

    val paddingS = dimensionResource(R.dimen.padding_s)
    val cardElevation = dimensionResource(R.dimen.card_elevation)
    val radiusM = dimensionResource(R.dimen.radius_m)
    val sizeImage = dimensionResource(R.dimen.size_image_item_movement)

    Card(
        modifier = modifier
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

}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "DefaultPreviewLight")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark")
@Composable
fun BankMovementItemLeftPreview() {
    StoriTestTheme {
        BankMovementItemLeft(
            modifier = Modifier,
            bankMovement = BankMovement(
                idTransaction = "consul",
                title = "mauris",
                date = "ad",
                amount = "ex",
                description = "torquent",
                type = "risus",
                status = false
            )

        )
    }
}