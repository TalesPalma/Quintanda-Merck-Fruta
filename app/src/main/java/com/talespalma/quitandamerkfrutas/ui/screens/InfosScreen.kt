package com.talespalma.quitandamerkfrutas.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.talespalma.quitandamerkfrutas.viewModels.HomeViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun InfosScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(viewModel.uiState.value.precoVenda.toString())
    }
}

@Preview
@Composable
private fun InfosScreenPreview() {
    InfosScreen()
}