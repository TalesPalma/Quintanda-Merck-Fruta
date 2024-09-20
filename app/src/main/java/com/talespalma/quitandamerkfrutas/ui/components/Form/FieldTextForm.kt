package com.talespalma.quitandamerkfrutas.ui.components.Form

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun FieldTextForm(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    val focusRequester = remember { FocusRequester() }
    OutlinedTextField(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .padding(top = 5.dp)
            .focusRequester(focusRequester),
        value = value,
        onValueChange = {
            onValueChange(it)
            //Move cursor para o final
            focusRequester.requestFocus()
        },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Next
        ),
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

}