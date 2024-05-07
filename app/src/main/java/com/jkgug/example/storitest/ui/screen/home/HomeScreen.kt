package com.jkgug.example.storitest.ui.screen.home

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jkgug.example.storitest.R
import com.jkgug.example.storitest.ui.components.home.HomeBottom
import com.jkgug.example.storitest.ui.components.home.HomeFake
import com.jkgug.example.storitest.ui.components.home.HomeTop
import com.jkgug.example.storitest.ui.theme.StoriTestTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
    onMovementClick: (String) -> Unit = {},
) {

    val paddingL = dimensionResource(R.dimen.padding_l)

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        HomeTop(
            userName = uiState.userName,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(paddingL))
        HomeFake(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(paddingL))
        HomeBottom(
            uiState.movements,
            uiState.messageForUser,
            isLoading = uiState.loadingContent,
            { viewModel.getMovementsData() },
            onMovementClick,
            modifier = Modifier.fillMaxWidth()
        )
    }

}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_NO, name = "DefaultPreviewLight")
@Preview(uiMode = UI_MODE_NIGHT_YES, name = "DefaultPreviewDark")
@Composable
fun HomeScreenPreview() {
    StoriTestTheme {
        HomeScreen(
            viewModel = koinViewModel(),
            modifier = Modifier.fillMaxSize(),
            onMovementClick = { _ ->
            }
        )
    }
}