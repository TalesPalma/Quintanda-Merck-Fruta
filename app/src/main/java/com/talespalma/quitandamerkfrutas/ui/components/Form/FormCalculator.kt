package com.talespalma.quitandamerkfrutas.ui.components.Form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.talespalma.quitandamerkfrutas.R
import com.talespalma.quitandamerkfrutas.database.Product
import com.talespalma.quitandamerkfrutas.helpers.formatCurrency
import com.talespalma.quitandamerkfrutas.helpers.parseCurrencyInput
import com.talespalma.quitandamerkfrutas.viewModels.HomeUiState
import com.talespalma.quitandamerkfrutas.viewModels.HomeViewModel


@Composable
fun FormCalculator(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onClickButtonGenerate: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    if (uiState.formView) {
        FormCustoFixo(formViewModel = viewModel, onClickCloseScreen = {
            viewModel.updateFormView(false)
        }
        )
    } else {
        AllFieldsForm(modifier, uiState, viewModel, onClickButtonGenerate)
    }
}

@Composable
private fun AllFieldsForm(
    modifier: Modifier,
    uiState: HomeUiState,
    viewModel: HomeViewModel,
    onClickButtonGenerate: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FieldTextForm(
            modifier = modifier,
            value = uiState.productName,
            onValueChange = {
                viewModel.updateProductName(it)
            },
            label = stringResource(R.string.nome).uppercase()
        )
        Spacer(modifier = Modifier.padding(10.dp))
        FieldTextForm(
            modifier = modifier,
            value = formatCurrency(uiState.custoFixo),
            onValueChange = {
                viewModel.updateCustoFixo(parseCurrencyInput(it))
            },
            label = stringResource(R.string.custo_fixo).uppercase(),
        )
        Button(onClick = { viewModel.updateFormView(true) }, shape = RectangleShape) {
            Text("Adicione elementos do produto")
        }
        Spacer(modifier = Modifier.padding(10.dp))
        FieldTextForm(
            modifier = modifier,
            value = formatCurrency(uiState.custoVariavel),
            onValueChange = {
                viewModel.updateCustoVariavel(parseCurrencyInput(it))
            },
            label = stringResource(R.string.custo_variavel).uppercase()
        )
        FieldTextForm(
            modifier = modifier,
            value = uiState.quantidade.toString(),
            onValueChange = {
                viewModel.updateQuantidade(it.toIntOrNull() ?: 0)
            },
            label = stringResource(R.string.quantiade).uppercase()
        )

        BarPercetagemLucro(uiState.margemLucro) { newValue ->
            viewModel.updateMargemLucro(newValue)
        }

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.saveProduct(product = Product(
                    name = uiState.productName,
                    price = uiState.precoVenda.toDouble(),
                ))
                onClickButtonGenerate()
            }
        ) {
            Text(text = "Salvar Produto")
        }
        Spacer(
            modifier = Modifier.padding(10.dp)
        )
        Text(
            "Preco de venda : ${uiState.precoVenda}",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
        )
    }
}


@Preview(showSystemUi = true)
@Composable
private fun FormCalculatorPreview() {
    FormCalculator() {}
}