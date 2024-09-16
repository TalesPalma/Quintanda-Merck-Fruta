package com.talespalma.quitandamerkfrutas.ui.components.Form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.talespalma.quitandamerkfrutas.helpers.formatCurrency
import com.talespalma.quitandamerkfrutas.helpers.parseCurrencyInput
import com.talespalma.quitandamerkfrutas.viewModels.HomeViewModel


@Composable
fun FormCalculator(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = HomeViewModel(),
    onClickButtonGenerate: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FieldTextForm(
            modifier = modifier,
            value = formatCurrency(uiState.custoFixo),
            onValueChange = {
                viewModel.updateCustoFixo(parseCurrencyInput(it))
            },
            label = "Custo fixo"
        )
        FieldTextForm(
            modifier = modifier,
            value = formatCurrency(uiState.custoVariavel),
            onValueChange = {
                viewModel.updateCustoVariavel(parseCurrencyInput(it))
            },
            label = "Custo variavel"
        )
        FieldTextForm(
            modifier = modifier,
            value = uiState.quantidade.toString(),
            onValueChange = {
                viewModel.updateQuantidade(it.toIntOrNull() ?: 0)
            },
            label = "Quatidade de produto"
        )


        BarPercetagemLucro(uiState.margemLucro) { newValue ->
            viewModel.updateMargemLucro(newValue)
        }

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onClickButtonGenerate
        ) {
            Text(text = "Gerar Relatorio")
        }
        Text(
            "Preco de venda : ${uiState.precoVenda}",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
    }
}


@Preview(showSystemUi = true)
@Composable
private fun FormCalculatorPreview() {
    FormCalculator() {}
}