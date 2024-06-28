package com.dimadyuk.newsapp.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dimadyuk.newsapp.BottomMenuScreen
import com.dimadyuk.newsapp.R

@Composable
fun BottomMenu(navController: NavHostController) {
    val menuItems = listOf(
        BottomMenuScreen.TopNews,
        BottomMenuScreen.Categories,
        BottomMenuScreen.Sources
    )

    BottomAppBar(
        contentColor = colorResource(id = R.color.white),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        menuItems.forEach { screen ->
            NavigationBarItem(
                label = {
                    Text(text = screen.title)
                },
                alwaysShowLabel = true,
                selected = currentRoute == screen.route,
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = null
                    )
                },
                onClick = {
                    navController.navigate(screen.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}