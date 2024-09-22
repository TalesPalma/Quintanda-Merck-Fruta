package com.talespalma.quitandamerkfrutas.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.talespalma.quitandamerkfrutas.models.Produto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal
import javax.inject.Inject



data class FormCustoFixoUiState(
    val listProducts: SnapshotStateList<Produto> = mutableStateListOf(),
)

@HiltViewModel
class FormCustoFixoViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(FormCustoFixoUiState())
    val uiState: StateFlow<FormCustoFixoUiState> = _uiState.asStateFlow()

    private val _listProducts = mutableStateListOf<Produto>()

    init {
        _uiState.value = FormCustoFixoUiState(
            listProducts = _listProducts
        )
    }

    fun updateListProducts(){
        _listProducts.add(Produto())
        _uiState.update { currentState ->
            currentState.copy(listProducts = _listProducts)
        }
    }

}