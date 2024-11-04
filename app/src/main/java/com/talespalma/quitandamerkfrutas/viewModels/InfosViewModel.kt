package com.talespalma.quitandamerkfrutas.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talespalma.quitandamerkfrutas.database.Product
import com.talespalma.quitandamerkfrutas.database.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class InfosViewModel @Inject constructor(
    private val repository : ProductRepository
) : ViewModel() {

    private val _products = mutableStateListOf<Product>()
    val products  = _products

    private var _onLoading by mutableStateOf(false)
    val onLoading: Boolean
        get() = _onLoading


    init {
        loadProducts()
    }

    private fun loadProducts(){
         viewModelScope.launch {
             _products.addAll(repository.getAllProducts())
         }
    }

     fun deleteProduct(product: Product){
        viewModelScope.launch {
            repository.deleteProduct(product)
            _products.remove(product)
        }
    }

}