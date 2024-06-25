package com.dimadyuk.newsapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dimadyuk.newsapp.MockData
import com.dimadyuk.newsapp.MockData.getTimeAgo
import com.dimadyuk.newsapp.R
import com.dimadyuk.newsapp.model.NewsData
import com.dimadyuk.newsapp.ui.theme.NewsAppTheme

@Composable
fun DetailScreen(
    navController: NavController, newsData: NewsData, scrollState: ScrollState
) {

    Scaffold(
        topBar = {
            DetailsTopAppBar(onBackPressed = {
                navController.popBackStack()
            })
        }) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Detail screen", fontWeight = FontWeight.SemiBold)
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = newsData.imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoWithIcon(
                    info = newsData.author, icon = Icons.Outlined.Edit
                )
                MockData.stringToDate(newsData.publishedAt)?.let {
                    InfoWithIcon(
                        info = it.getTimeAgo(), icon = Icons.Outlined.DateRange
                    )
                }
            }
            Text(
                modifier = Modifier.padding(8.dp), text = newsData.title, fontWeight = FontWeight.Bold
            )
            Text(
                text = newsData.description
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopAppBar(onBackPressed: () -> Unit) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = {
            Text(text = "Detail screen", fontWeight = FontWeight.SemiBold)
        },
        navigationIcon = {
            Icon(modifier = Modifier
                .clickable { onBackPressed() }
                .padding(6.dp),
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = null)
        },
    )
}

@Composable
fun InfoWithIcon(info: String, icon: ImageVector) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(6.dp),
            imageVector = icon,
            contentDescription = null,
            tint = colorResource(id = R.color.purple_500)
        )
        Text(text = info)
    }
}
@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    NewsAppTheme {
        DetailScreen(
            navController = rememberNavController(),
            newsData = NewsData(
                id = 1,
                author = "author",
                title = "title",
                description = "description",
                publishedAt = "publishedAt"
            ),
            scrollState = rememberScrollState(),
        )
    }
}
