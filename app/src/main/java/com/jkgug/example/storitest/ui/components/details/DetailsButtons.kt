package com.jkgug.example.storitest.ui.components.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
fun DetailsButtons(
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
    ) {
        IconButton(onClick = {}) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Localized description")
        }
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterEnd
        ) {
            Row {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Share, contentDescription = "Localized description")
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Face, contentDescription = "Localized description")
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "DefaultPreviewLight")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark")
@Composable
fun DetailsButtonsPreview() {
    StoriTestTheme {
        DetailsButtons(
            modifier = Modifier.fillMaxWidth()
        )
    }
}