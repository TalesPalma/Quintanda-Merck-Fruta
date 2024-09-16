package com.talespalma.quitandamerkfrutas.viewModels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject


data class HomeUiState(
    val custoFixo: BigDecimal = BigDecimal.ZERO,
    val custoVariavel: BigDecimal = BigDecimal.ZERO,
    val quantidade: Int = 0, // Alterado para Int
    val margemLucro: BigDecimal = BigDecimal.ZERO,
    val precoVenda: BigDecimal = BigDecimal.ZERO,
)


@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _stateUpdate = MutableStateFlow(false)
    val stateUpdate: StateFlow<Boolean> = _stateUpdate.asStateFlow()


    fun updateCustoFixo(custoFixo: BigDecimal) {
        _uiState.update { currentState ->
            currentState.copy(custoFixo = custoFixo)
        }
        calculatorPriceFinally()
    }

    fun updateCustoVariavel(custoVariavel: BigDecimal) {
        _uiState.update { currentState ->
            currentState.copy(custoVariavel = custoVariavel)
        }
        calculatorPriceFinally()
    }

    fun updateQuantidade(quantidade: Int) { // Alterado para Int
        _uiState.update { currentState ->
            currentState.copy(quantidade = quantidade)
        }
        calculatorPriceFinally()
    }

    fun updateMargemLucro(margemLucro: BigDecimal) {
        _uiState.update { currentState ->
            currentState.copy(margemLucro = margemLucro)
        }
        calculatorPriceFinally()
    }


    fun calculatorPriceFinally() {
        val custoTotal = _uiState.value.custoFixo +
                (_uiState.value.custoVariavel * BigDecimal(_uiState.value.quantidade))
        val custoComMargem = custoTotal + (custoTotal * _uiState.value.margemLucro)

        // Verifique se a quantidade é maior que zero para evitar divisão por zero
        val precoUnitario = if (_uiState.value.quantidade > 0) {
            custoComMargem.divide(BigDecimal(_uiState.value.quantidade), 2, RoundingMode.HALF_EVEN)
        } else {
            BigDecimal.ZERO
        }

        _uiState.update { currentState ->
            currentState.copy(precoVenda = precoUnitario)
        }

        _stateUpdate.value = true
    }


}