package com.dimadyuk.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.dimadyuk.newsapp.components.ErrorUI
import com.dimadyuk.newsapp.components.LoadingUI
import com.dimadyuk.newsapp.ui.MainViewModel
import com.dimadyuk.newsapp.ui.NewsApp
import com.dimadyuk.newsapp.ui.theme.NewsAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        NewsApp(
                            viewModel = viewModel
                        )
                        if (viewModel.isLoading.collectAsState().value) {
                            LoadingUI()
                        }
                        if (viewModel.isError.collectAsState().value) {
                            ErrorUI()
                        }
                    }
                }
            }
        }
        viewModel.onSelectedCategoryChanged("business")
        viewModel.getArticlesByCategory("business")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val app = LocalContext.current.applicationContext as MainApp
    val viewModel = MainViewModel(app)
    NewsAppTheme {
        NewsApp(
            viewModel = viewModel
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Loading(modifier: Modifier = Modifier) {
    val app = LocalContext.current.applicationContext as MainApp
    val viewModel = MainViewModel(app)
    NewsAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),

                ) {
                NewsApp(
                    viewModel = viewModel
                )
                if (true) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}
