package com.talespalma.quitandamerkfrutas.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.talespalma.quitandamerkfrutas.ui.components.ProductItem
import com.talespalma.quitandamerkfrutas.viewModels.HomeViewModel
import com.talespalma.quitandamerkfrutas.viewModels.InfosViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun InfosScreen(
    modifier: Modifier = Modifier,
    viewModel: InfosViewModel = hiltViewModel()
) {

    Column(
        modifier.padding(top = 50.dp, start = 16.dp, end = 16.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn {
            items(viewModel.products) { product ->
                ProductItem(product = product, onDeleteClick = {
                    viewModel.deleteProduct(product)
                })
            }
        }
    }
}

@Preview
@Composable
private fun InfosScreenPreview() {
    InfosScreen()
}