package com.talespalma.quitandamerkfrutas.ui.components.Form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog

@Composable
fun FormCustoFixo(
    modifier: Modifier = Modifier,
    onClickCloseScreen: () -> Unit = {},
    listProdutos: SnapshotStateList<String>
) {
    Dialog(onDismissRequest = onClickCloseScreen) {
        Surface {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                items(listProdutos) {
                    Text(it)
                    TextField(value = "", onValueChange = {})
                }
            }
            Button(onClick = {
                listProdutos.add("Novo item")
            }) {
                Text(text = "Add new item")
            }
        }
    }
}


@Preview
@Composable
private fun FormCustoFixoPreview() {
//    FormCustoFixo()
}