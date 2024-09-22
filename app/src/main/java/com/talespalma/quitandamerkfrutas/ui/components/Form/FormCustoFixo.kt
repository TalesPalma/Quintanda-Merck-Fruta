package com.talespalma.quitandamerkfrutas.ui.components.Form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.talespalma.quitandamerkfrutas.helpers.parseCurrencyInput
import com.talespalma.quitandamerkfrutas.viewModels.FormCustoFixoViewModel
import com.talespalma.quitandamerkfrutas.viewModels.HomeViewModel

@Composable
fun FormCustoFixo(
    modifier: Modifier = Modifier,
    onClickCloseScreen: () -> Unit = {},
    formViewModel: HomeViewModel = HomeViewModel(),
    viewModel: FormCustoFixoViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    Dialog(onDismissRequest = onClickCloseScreen) {
        Surface {
            Column(Modifier.fillMaxSize()) {
                Row {
                    Button(onClick = {
                        viewModel.updateListProducts()
                    })
                    {
                        Text(text = "Add new item")
                    }
                    Button(onClick = {
                        val allCustoFixos = uiState.listProducts.sumOf { it.preco }
                        formViewModel.updateCustoFixo(allCustoFixos)
                        onClickCloseScreen()
                    }) {
                        Text("Somar")
                    }
                }
                LazyColumn(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                ) {
                    items(uiState.listProducts) { product ->
                        OutlinedTextField(
                            value = product.nome,
                            onValueChange = {
                            }
                        )
                        OutlinedTextField(
                            value = product.preco.toString(),
                            onValueChange = {
                            }
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun FormCustoFixoPreview() {
    FormCustoFixo()
}