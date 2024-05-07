package com.jkgug.example.storitest.ui.screen.success

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jkgug.example.storitest.R
import com.jkgug.example.storitest.ui.theme.StoriTestTheme
import kotlinx.coroutines.delay

const val TIME_SUCCESS = 2000L

@Composable
fun SuccessScreen(
    modifier: Modifier = Modifier,
    onSuccessEndNavigation: () -> Unit
) {

    val paddingS = dimensionResource(id = R.dimen.padding_s)
    val paddingL = dimensionResource(id = R.dimen.padding_l)
    val paddingXXXL = dimensionResource(id = R.dimen.padding_xxxl)
    val radiusXXL = dimensionResource(id = R.dimen.radius_xxl)
    val cardElevation = dimensionResource(id = R.dimen.card_elevation)
    val sizeSuccessIcon = dimensionResource(id = R.dimen.size_success_icon)

    Column(
        modifier = modifier.padding(
            start = paddingL, end = paddingL,
            top = paddingXXXL, bottom = paddingXXXL
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.success_title),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .size(sizeSuccessIcon)
                    .padding(paddingS),
                elevation = CardDefaults.cardElevation(defaultElevation = cardElevation),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Green,
                ),
                shape = RoundedCornerShape(radiusXXL),
            ) {
                Icon(
                    Icons.Filled.Check,
                    tint = MaterialTheme.colorScheme.background,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(
                        128.dp
                    )
                )
            }
        }
        Text(
            modifier = Modifier.padding(horizontal = paddingL),
            text = stringResource(R.string.success_message),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
    }

    LaunchedEffect(Unit) {
        delay(TIME_SUCCESS)
        onSuccessEndNavigation()
    }

}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "DefaultPreviewLight")
@Composable
fun SuccessScreenPreview() {
    StoriTestTheme {
        SuccessScreen(
            modifier = Modifier.fillMaxSize(),
            onSuccessEndNavigation = {},
        )
    }
}