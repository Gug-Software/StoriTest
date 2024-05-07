package com.jkgug.example.storitest.ui.components.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.jkgug.example.storitest.R

@Composable
fun IndicatorView(
    modifier: Modifier = Modifier
) {

    val paddingXS = dimensionResource(R.dimen.padding_xs)
    val paddingL = dimensionResource(R.dimen.padding_l)

    Box(
        modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center
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
}