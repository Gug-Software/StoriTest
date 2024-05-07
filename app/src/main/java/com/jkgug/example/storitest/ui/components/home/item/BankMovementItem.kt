package com.jkgug.example.storitest.ui.components.home.item

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jkgug.example.storitest.R
import com.jkgug.example.storitest.data.BankMovement
import com.jkgug.example.storitest.ui.theme.StoriTestTheme

@Composable
fun BankMovementItem(
    bankMovement: BankMovement,
    modifier: Modifier = Modifier
) {

    val paddingXS = dimensionResource(R.dimen.padding_xs)

    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {

        val (leftContent, rightContent) = createRefs()
        BankMovementItemLeft(
            modifier = Modifier.constrainAs(leftContent) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        )

        BankMovementItemRight(bankMovement = bankMovement,
            modifier = Modifier
                .constrainAs(rightContent) {
                    start.linkTo(leftContent.end, paddingXS)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                }
        )
    }

}

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Composable
fun BankMovementItemPreview() {
    StoriTestTheme {
        BankMovementItem(
            bankMovement = BankMovement(
                idTransaction = "prodesset",
                title = "Compra",
                date = "cu",
                amount = "dolore",
                description = "",
                type = "referrentur",
                status = false
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}