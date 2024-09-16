package com.talespalma.quitandamerkfrutas.viewModels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject


data class HomeData(
    val custoFixo: BigDecimal = BigDecimal.ZERO,
    val custoVariavel: BigDecimal = BigDecimal.ZERO,
    val quantidade: Int = 0, // Alterado para Int
    val margemLucro: BigDecimal = BigDecimal.ZERO,
    val precoVenda: BigDecimal = BigDecimal.ZERO,
)


@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(HomeData())
    val uiState: StateFlow<HomeData> = _uiState.asStateFlow()

    fun updateCustoFixo(custoFixo: BigDecimal) {
        _uiState.value = _uiState.value.copy(custoFixo = custoFixo)
    }

    fun updateCustoVariavel(custoVariavel: BigDecimal) {
        _uiState.value = _uiState.value.copy(custoVariavel = custoVariavel)
    }

    fun updateQuantidade(quantidade: Int) { // Alterado para Int
        _uiState.value = _uiState.value.copy(quantidade = quantidade)
    }

    fun updateMargemLucro(margemLucro: BigDecimal) {
        _uiState.value = _uiState.value.copy(margemLucro = margemLucro)
    }


    fun calculatorPriceFinally() {
        val custoTotal = _uiState.value.custoFixo
            .add(_uiState.value.custoVariavel)
            .add(_uiState.value.custoVariavel.multiply(_uiState.value.margemLucro))

        // Verifique se a quantidade é maior que zero para evitar divisão por zero
        val precoUnitario = if (_uiState.value.quantidade > 0) {
            val custoPorPeca = custoTotal.divide(BigDecimal(_uiState.value.quantidade), 2, RoundingMode.HALF_EVEN)
            custoPorPeca.add(custoPorPeca.multiply(_uiState.value.margemLucro))
        } else {
            BigDecimal.ZERO
        }

        _uiState.value = _uiState.value.copy(precoVenda = precoUnitario)
    }


}