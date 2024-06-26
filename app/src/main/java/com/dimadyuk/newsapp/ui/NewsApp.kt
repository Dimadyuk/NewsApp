package com.dimadyuk.newsapp.ui

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dimadyuk.newsapp.BottomMenuScreen
import com.dimadyuk.newsapp.components.BottomMenu
import com.dimadyuk.newsapp.model.TopNewsArticle
import com.dimadyuk.newsapp.network.NewsManager
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
    }) { padding ->
        Navigation(
            modifier = Modifier.padding(padding),
            navController = navController,
            scrollState = scrollState
        )
    }
}

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    scrollState: ScrollState,
    newsManager: NewsManager = NewsManager()
) {
    val articles = newsManager.newsResponse.value.articles
    Log.d("articles", "$articles")
    articles?.let {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = BottomMenuScreen.TopNews.route
        ) {
            bottomNavigation(
                navController = navController,
                articles = articles,
                newsManager = newsManager
            )
            composable(
                "DetailScreen/{index}",
                arguments = listOf(navArgument("index") { type = NavType.IntType })
            ) { navBackStackEntry ->
                navBackStackEntry.arguments?.getInt("index")?.let { index ->
                    DetailScreen(
                        navController = navController,
                        scrollState = scrollState,
                        article = articles[index]
                    )
                }
            }
        }
    }
}

fun NavGraphBuilder.bottomNavigation(
    navController: NavController,
    articles: List<TopNewsArticle>,
    newsManager: NewsManager,
) {
    composable(BottomMenuScreen.TopNews.route) {
        TopNewsScreen(navController = navController, articles)
    }

    composable(BottomMenuScreen.Categories.route) {
        newsManager.onSelectedCategoryChanged("business")
        newsManager.getArticlesByCategory("business")
        CategoriesScreen(
            onFetchCategory = {
                newsManager.onSelectedCategoryChanged(it)
                newsManager.getArticlesByCategory(it)
            },
            newsManager = newsManager
        )
    }
    composable(BottomMenuScreen.Sources.route) {
        SourcesScreen(
            newsManager = newsManager
        )
    }
}
