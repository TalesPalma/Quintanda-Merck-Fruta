package com.talespalma.quitandamerkfrutas.ui.components.Form

import android.telephony.data.RouteSelectionDescriptor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.talespalma.quitandamerkfrutas.helpers.parseCurrencyInput
import com.talespalma.quitandamerkfrutas.viewModels.FormCustoFixoViewModel
import com.talespalma.quitandamerkfrutas.viewModels.HomeViewModel
import java.math.BigDecimal

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
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(600.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            )
            {
                Row(modifier.fillMaxWidth().background(Color.Black), horizontalArrangement = Arrangement.SpaceAround) {
                    IconButton(onClick = onClickCloseScreen) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close",
                        )
                    }
                    Button(onClick = {
                        viewModel.addNewProductList()
                    })
                    {
                        Text(text = "New")
                    }
                    Button(
                        onClick = {
                        formViewModel.updateCustoFixo(BigDecimal.ZERO)
                        uiState.listProducts.clear()
                    }) {
                        Text("Limpar")
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
                    itemsIndexed(uiState.listProducts) { index, product ->
                        Box(modifier = Modifier.padding(10.dp)) {
                            Row(
                                modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    OutlinedTextField(
                                        value = product.nome,
                                        onValueChange = { name ->
                                            viewModel.updateProductList(name, product.preco, index)
                                        }
                                    )
                                    OutlinedTextField(
                                        value = product.preco.toString(),
                                        onValueChange = { price ->
                                            viewModel.updateProductList(
                                                product.nome,
                                                parseCurrencyInput(price),
                                                index
                                            )
                                        }
                                    )
                                }
                                IconButton(
                                    modifier = Modifier.weight(20F),
                                    onClick = {
                                        uiState.listProducts.removeAt(index)
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
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