package com.jkgug.example.storitest.ui.components.home.item

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jkgug.example.storitest.R
import com.jkgug.example.storitest.domain.BankMovement
import com.jkgug.example.storitest.ui.theme.StoriTestTheme

@Composable
fun BankMovementItemRight(
    bankMovement: BankMovement,
    modifier: Modifier = Modifier
) {

    val paddingS = dimensionResource(R.dimen.padding_s)

    ConstraintLayout(
        modifier = modifier.padding(vertical = paddingS)
    ) {
        val (leftContent, rightContent) = createRefs()
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .constrainAs(leftContent) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
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

        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .constrainAs(rightContent) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            val textColor = if (bankMovement.status) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.error
            }
            Text(
                text = bankMovement.amount,
                style = MaterialTheme.typography.titleLarge,
                color = textColor
            )
            if (bankMovement.description.isNotEmpty()) {
                Text(
                    text = bankMovement.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = textColor
                )
            }
        }
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
fun BankMovementItemRightPreview() {
    StoriTestTheme {
        BankMovementItemRight(
            bankMovement = BankMovement(
                idTransaction = "prodesset",
                title = "Compra",
                date = "cu",
                amount = "dolore",
                description = "nec",
                type = "referrentur",
                status = false
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}