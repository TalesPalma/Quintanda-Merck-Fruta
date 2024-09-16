package com.talespalma.quitandamerkfrutas.ui.components.Form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.math.BigDecimal
import java.math.RoundingMode

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