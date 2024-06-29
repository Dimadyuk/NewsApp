package com.dimadyuk.newsapp.ui

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.dimadyuk.newsapp.data.model.TopNewsArticle
import com.dimadyuk.newsapp.ui.screen.CategoriesScreen
import com.dimadyuk.newsapp.ui.screen.DetailScreen
import com.dimadyuk.newsapp.ui.screen.SourcesScreen
import com.dimadyuk.newsapp.ui.screen.TopNewsScreen

@Composable
fun NewsApp(
    viewModel: MainViewModel
) {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()

    MainScreen(
        navController = navController,
        scrollState = scrollState,
        viewModel = viewModel,
    )
}

@Composable
fun MainScreen(
    navController: NavHostController, scrollState: ScrollState, viewModel: MainViewModel
) {
    Scaffold(bottomBar = {
        BottomMenu(navController = navController)
    }) { padding ->
        Navigation(
            paddingValues = padding,
            navController = navController,
            scrollState = scrollState,
            viewModel = viewModel,
        )
    }
}

@Composable
fun Navigation(
    paddingValues: PaddingValues,
    navController: NavHostController,
    scrollState: ScrollState, viewModel: MainViewModel
) {
    val articles = viewModel.newsResponse.collectAsState().value.articles ?: emptyList()
    Log.d("articles", "$articles")
    val searchList = viewModel.searchedNewsResponse.collectAsState().value?.articles ?: emptyList()
    val resultList = searchList.ifEmpty { articles }

    NavHost(
        modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = BottomMenuScreen.TopNews.route
        ) {
            bottomNavigation(
                navController = navController,
                articles = resultList, viewModel = viewModel
            )
            composable(
                "DetailScreen/{index}",
                arguments = listOf(navArgument("index") { type = NavType.IntType })
            ) { navBackStackEntry ->
                navBackStackEntry.arguments?.getInt("index")?.let { index ->
                    DetailScreen(
                        navController = navController,
                        scrollState = scrollState,
                        article = resultList[index]
                    )
                }
            }
    }
}

fun NavGraphBuilder.bottomNavigation(
    navController: NavController,
    articles: List<TopNewsArticle>,
    viewModel: MainViewModel,
) {

    composable(BottomMenuScreen.TopNews.route) {
        viewModel.getTopArticles()
        TopNewsScreen(
            navController = navController,
            articles = articles,
            viewModel = viewModel,
        )
    }

    composable(BottomMenuScreen.Categories.route) {
        CategoriesScreen(
            onFetchCategory = {
                viewModel.onSelectedCategoryChanged(it)
                viewModel.getArticlesByCategory(it)
            }, viewModel = viewModel
        )
    }
    composable(BottomMenuScreen.Sources.route) {
        SourcesScreen(
            viewModel = viewModel
        )
    }
}
