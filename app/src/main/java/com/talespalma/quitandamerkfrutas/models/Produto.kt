package com.talespalma.quitandamerkfrutas.models

import java.math.BigDecimal

data class Produto(
    val nome: String = "",
    val preco: BigDecimal  = BigDecimal.ZERO,
)
