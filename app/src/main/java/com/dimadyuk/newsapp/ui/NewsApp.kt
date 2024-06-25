package com.dimadyuk.newsapp.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dimadyuk.newsapp.BottomMenuScreen
import com.dimadyuk.newsapp.MockData
import com.dimadyuk.newsapp.components.BottomMenu
import com.dimadyuk.newsapp.ui.screen.CategoriesScreen
import com.dimadyuk.newsapp.ui.screen.DetailScreen
import com.dimadyuk.newsapp.ui.screen.SourcesScreen
import com.dimadyuk.newsapp.ui.screen.TopNewsScreen

@Composable
fun NewsApp() {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    MainScreen(
        navController = navController,
        scrollState = scrollState
    )
}

@Composable
fun MainScreen(
    navController: NavHostController,
    scrollState: ScrollState
) {
    Scaffold(bottomBar = {
        BottomMenu(navController = navController)
    }) {
        Navigation(
            navController = navController,
            scrollState = scrollState
        )
    }
}

@Composable
fun Navigation(
    navController: NavHostController,
    scrollState: ScrollState
) {

    NavHost(
        navController = navController,
        startDestination = BottomMenuScreen.TopNews.route
    ) {
        bottomNavigation(
            navController = navController
        )
        composable(BottomMenuScreen.TopNews.route) {
            TopNewsScreen(navController = navController)
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

fun NavGraphBuilder.bottomNavigation(
    navController: NavController
) {
    composable(BottomMenuScreen.TopNews.route) {
        TopNewsScreen(navController = navController)
    }

    composable(BottomMenuScreen.Categories.route) {
        CategoriesScreen()
    }
    composable(BottomMenuScreen.Sources.route) {
        SourcesScreen()
    }
}
