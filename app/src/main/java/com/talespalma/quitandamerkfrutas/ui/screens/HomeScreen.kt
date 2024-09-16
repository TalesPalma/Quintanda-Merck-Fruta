package com.talespalma.quitandamerkfrutas.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.talespalma.quitandamerkfrutas.navigate.Screens
import com.talespalma.quitandamerkfrutas.ui.components.Form.FormCalculator
import com.talespalma.quitandamerkfrutas.viewModels.HomeViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FormCalculator(
            viewModel = viewModel,
            onClickButtonGenerate = {
                navController.navigate(Screens.Infos.route)
            }
        )
    }
}