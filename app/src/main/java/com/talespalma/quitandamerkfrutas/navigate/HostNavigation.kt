package com.talespalma.quitandamerkfrutas.navigate

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.talespalma.quitandamerkfrutas.ui.screens.HomeScreen
import com.talespalma.quitandamerkfrutas.ui.screens.InfosScreen
import com.talespalma.quitandamerkfrutas.viewModels.HomeViewModel


sealed class Screens(val route: String, val icon: ImageVector) {
    data object Home : Screens("Home", Icons.Filled.Home)
    data object Infos : Screens("Infos/", Icons.Filled.CheckCircle)
}

@Composable
fun HostNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        composable(route = Screens.Home.route) {
            HomeScreen(modifier = Modifier,viewModel = viewModel,navController = navController)
        }
        composable(
            route = Screens.Infos.route,
        ) {
            InfosScreen()
        }
    }
}