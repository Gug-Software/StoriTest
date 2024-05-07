package com.jkgug.example.storitest.ui.components.home

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jkgug.example.storitest.R
import com.jkgug.example.storitest.domain.BankMovement
import com.jkgug.example.storitest.ui.components.common.EmptyView
import com.jkgug.example.storitest.ui.components.home.item.BankMovementItem
import com.jkgug.example.storitest.ui.theme.StoriTestTheme

@Composable
fun HomeBottomContent(
    movements: List<BankMovement> = emptyList(),
    onMovementClick: (String) -> Unit = {},
) {

    val paddingS = dimensionResource(R.dimen.padding_s)

    if (movements.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(paddingS)
        ) {
            items(items = movements, itemContent = { item ->
                BankMovementItem(
                    bankMovement = item,
                    modifier = Modifier.clickable {
                        onMovementClick(item.idFromFireStore)
                    }
                )
            })
        }
    } else {
        EmptyView(
            textTitle = stringResource(id = R.string.empty_title),
            textSubtitle = stringResource(id = R.string.empty_subtitle),
            modifier = Modifier.fillMaxWidth()
        )
    }

}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "DefaultPreviewLight")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark")
@Composable
fun HomeBottomContentPreview() {
    StoriTestTheme {
        HomeBottomContent(
            movements = listOf(
                BankMovement(
                    idTransaction = "doming",
                    title = "iusto",
                    date = "pulvinar",
                    amount = "dolorum",
                    description = "oporteat",
                    type = "ignota",
                    status = false
                )
            ),
            onMovementClick = {}
        )
    }
}