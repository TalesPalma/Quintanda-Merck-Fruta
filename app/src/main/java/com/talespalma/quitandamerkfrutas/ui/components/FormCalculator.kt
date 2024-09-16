package com.talespalma.quitandamerkfrutas.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.talespalma.quitandamerkfrutas.helpers.formatCurrency
import com.talespalma.quitandamerkfrutas.helpers.parseCurrencyInput
import com.talespalma.quitandamerkfrutas.viewModels.HomeViewModel
import java.math.BigDecimal
import java.math.RoundingMode


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
        LaunchedEffect(key1 = uiState.margemLucro) {
            viewModel.calculatorPriceFinally()
        }
        Text(
            "Preco de venda : ${uiState.precoVenda}",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarPercetagemLucro(
    value: BigDecimal,
    onValueChange: (BigDecimal) -> Unit
) {
    Column(
        Modifier.padding(top = 10.dp)
    ) {
        Text(
            text = "Selecione a margem de lucro",
            style = MaterialTheme.typography.titleMedium
        )
        Slider(
            value = value.toFloat(),
            onValueChange = {
                onValueChange(it.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
            },
            valueRange = 0f..1f,
            steps = 9,
            thumb = {
                Text(
                    text = "${
                        value.multiply(BigDecimal(100)).setScale(0, RoundingMode.HALF_EVEN)
                    }%",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        )
    }
}

@Composable
fun FieldTextForm(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    TextField(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .padding(top = 5.dp),
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Next
        ),
    )
}


@Preview(showSystemUi = true)
@Composable
private fun FormCalculatorPreview() {
    FormCalculator() {}
}