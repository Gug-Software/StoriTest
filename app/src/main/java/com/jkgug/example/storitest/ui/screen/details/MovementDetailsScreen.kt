package com.jkgug.example.storitest.ui.screen.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jkgug.example.storitest.R
import com.jkgug.example.storitest.ui.components.common.ErrorView
import com.jkgug.example.storitest.ui.components.details.DetailsBottom
import com.jkgug.example.storitest.ui.components.details.DetailsButtons
import com.jkgug.example.storitest.ui.components.details.DetailsTop
import com.jkgug.example.storitest.ui.theme.StoriTestTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovementDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: MovementDetailsViewModel = koinViewModel(),
    onBackNavigation: () -> Unit,
) {

    val paddingXL = dimensionResource(R.dimen.padding_xl)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val paddingM = dimensionResource(R.dimen.padding_m)
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        DetailsButtons(
            onBackNavigation = onBackNavigation,
            modifier = Modifier.padding(paddingM)
        )
        if (uiState.loadingContent) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = paddingM)
            )
        } else {
            Box {
                uiState.messageForUser?.let {
                    ErrorView(
                        { viewModel.getMovementsData() },
                        modifier = Modifier.fillMaxWidth()
                    )
                } ?: run {
                    Column {
                        DetailsTop(
                            uiState.bankMovement,
                            modifier = Modifier.padding(top = paddingXL)
                        )
                        DetailsBottom(uiState.bankMovement)
                    }
                }
            }

        }

    }

}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "DefaultPreviewLight")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark")
@Composable
fun HomeTopPreview() {
    StoriTestTheme {
        MovementDetailsScreen(
            modifier = Modifier.fillMaxWidth(),
            viewModel = koinViewModel(),
            onBackNavigation = { }
        )
    }
}