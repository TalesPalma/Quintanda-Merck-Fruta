package com.talespalma.quitandamerkfrutas.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController


@Composable
fun MenuBottom(modifier: Modifier = Modifier,navController: NavController?) {
    BottomAppBar(containerColor = Color.Black){
            IconButton(onClick = { navController?.navigate("home") }) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home", tint = Color.White)
            }
            IconButton(onClick = { navController?.navigate("2") }) {
                Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "Home",tint = Color.White)
            }
    }
}


@Preview
@Composable
private fun MenuBottomPreview() {
    MenuBottom(navController = null)
}