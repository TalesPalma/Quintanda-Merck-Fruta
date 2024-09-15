package com.talespalma.quitandamerkfrutas.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormCalculator(modifier: Modifier = Modifier) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally){

        FieldTextForm(
            modifier = modifier,
            value = "",
            onValueChange = {},
            label = "Custo fixo"
        )
        FieldTextForm(
            modifier = modifier,
            value = "",
            onValueChange = {},
            label = "Custo variavel"
        )
        FieldTextForm(
            modifier = modifier,
            value = "",
            onValueChange = {},
            label = "Quatidade de produto"
        )

        val teste = remember {
            mutableFloatStateOf(0.0F)
        }
        BarPercetagemLucro(teste.floatValue){ newValue ->
            teste.floatValue = newValue
        }
        Button(onClick = {}) {
            Text(text = "Gerar")
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarPercetagemLucro(
    value:Float ,
    onValueChange: (Float) -> Unit
){
    Column(
        Modifier.padding(top = 10.dp)
    ){
        Text(
            text = "Selecione a margem de lucro",
            style = MaterialTheme.typography.titleMedium
        )
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = 0f..1f,
            steps = 9,
            thumb = {
                Text(
                    text = "${(value * 100).roundToInt()}%",
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
    )
}


@Preview(showSystemUi = true)
@Composable
private fun FormCalculatorPreview() {
    FormCalculator()
}