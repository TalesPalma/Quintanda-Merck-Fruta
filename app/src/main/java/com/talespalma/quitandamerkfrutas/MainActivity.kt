package com.talespalma.quitandamerkfrutas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.talespalma.quitandamerkfrutas.navigate.HostNavigation
import com.talespalma.quitandamerkfrutas.ui.components.MenuBottom
import com.talespalma.quitandamerkfrutas.ui.theme.QuitandaMerkFrutasTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuitandaMerkFrutasTheme {
                AppHost(
                    name = "Gerar Nova release",
                )
            }
        }
    }
}


@Composable
fun AppHost(name: String) {
    val navController = rememberNavController()
    Scaffold(modifier = Modifier.fillMaxSize()
        , topBar = {},
        bottomBar = {
            MenuBottom(navController = navController)
        }
    ) { innerPadding ->
        HostNavigation(
            modifier = Modifier.padding(innerPadding),
            navController = navController
        )
    }
}

