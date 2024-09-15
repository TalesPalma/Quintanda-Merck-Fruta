package com.talespalma.quitandamerkfrutas.navigate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.talespalma.quitandamerkfrutas.ui.screens.HomeScreen


sealed class Screens(val route: String, val icon: ImageVector) {
    data object Home : Screens("Home", Icons.Filled.Home)
    data object Add : Screens("Add", Icons.Filled.AddCircle)
}

@Composable
fun HostNavigation(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable(Screens.Home.route) {
            HomeScreen()
        }
        composable(Screens.Add.route) {
            Column(
                modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Tela 2")
            }
        }
    }
}