package com.talespalma.quitandamerkfrutas.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.talespalma.quitandamerkfrutas.navigate.Screens


val listRoutes: List<Screens> = listOf(Screens.Home, Screens.Add)

@Composable
fun MenuBottom(modifier: Modifier = Modifier, navController: NavController?) {


    var selectedItem by remember { mutableIntStateOf(0) }
    NavigationBar(
        contentColor = Color.White,
        containerColor = Color.Black
    ) {
        listRoutes.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.route
                    )
                },
                label = { item.route },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController?.navigate(item.route)
                }
            )
        }
    }
}


@Preview
@Composable
private fun MenuBottomPreview() {
    MenuBottom(navController = null)
}