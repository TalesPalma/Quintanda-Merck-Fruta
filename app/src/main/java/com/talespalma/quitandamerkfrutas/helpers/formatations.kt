package com.talespalma.quitandamerkfrutas.helpers

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun parseCurrencyInput(input: String): BigDecimal {
    val cleanInput = input.replace("[^\\d.,]".toRegex(), "") // Remove caracteres não numéricos
    val normalizedInput = cleanInput.replace(',', '.') // Substitui vírgula por ponto
    return normalizedInput.toBigDecimalOrNull() ?: BigDecimal.ZERO
}

fun formatCurrency(value: BigDecimal): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return formatter.format(value)
}
