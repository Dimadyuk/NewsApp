package com.dimadyuk.newsapp.ui

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dimadyuk.newsapp.MockData
import com.dimadyuk.newsapp.ui.screen.DetailScreen
import com.dimadyuk.newsapp.ui.screen.TopNews

@Composable
fun NewsApp() {
    Navigation()
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val scrollState = rememberScrollState()
    NavHost(
        navController = navController,
        startDestination = "TopNews"
    ) {
        composable("TopNews") {
            TopNews(navController = navController)
        }
        composable(
            "DetailScreen/{newsId}",
            arguments = listOf(navArgument("newsId") { type = NavType.IntType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getInt("newsId")?.let { newsId ->
                DetailScreen(
                    navController = navController,
                    scrollState = scrollState,
                    newsData = MockData.getNews(newsId)
                )
            }
        }
    }
}
