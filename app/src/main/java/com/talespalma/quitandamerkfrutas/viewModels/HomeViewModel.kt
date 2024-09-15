package com.talespalma.quitandamerkfrutas.viewModels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject



data class HomeData(
    val name:String = "OLA PESSOAL TESTANDO HILT",
    val age:Int = 0,
)



@HiltViewModel
class HomeViewModel @Inject constructor()  :ViewModel() {

    private val _uiState = MutableStateFlow(HomeData())
    val uiState: StateFlow<HomeData> = _uiState.asStateFlow()

}