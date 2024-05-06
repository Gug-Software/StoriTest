package com.jkgug.example.storitest.ui.components.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jkgug.example.storitest.ui.theme.StoriTestTheme

@Composable
fun CommonFormField(
    placeHolderString: String,
    onValueChanged: (String) -> Unit,
    value: String,
    showIsError: Boolean = false,
    enabled: Boolean = true,
    modifier: Modifier
) {

    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.AccountCircle,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }

    TextField(
        singleLine = true,
        value = value,
        onValueChange = onValueChanged,
        modifier = modifier,
        leadingIcon = leadingIcon,
        placeholder = {
            Text(
                text = placeHolderString,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
        ),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        isError = showIsError,
        enabled = enabled
    )
}

@Preview(showBackground = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun CommonFormFieldPreview() {
    StoriTestTheme {
        CommonFormField(
            placeHolderString = "place holder",
            onValueChanged = {},
            value = "Alo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            enabled = true

        )
    }
}