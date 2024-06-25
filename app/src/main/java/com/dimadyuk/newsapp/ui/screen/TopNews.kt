package com.dimadyuk.newsapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
fun TopNewsScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Top News", fontWeight = FontWeight.SemiBold)
        val news = MockData.topNewsList
        LazyColumn {
            items(news.size) { index ->
                TopNewsItem(
                    newsData = news[index],
                    onNewsClick = {
                        navController.navigate("DetailScreen/${it.id}")
                    }
                )
            }
        }
    }
}

@Composable
fun TopNewsItem(
    newsData: NewsData,
    onNewsClick: (NewsData) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
            .clickable {
                onNewsClick(newsData)
            }
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = newsData.imageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            MockData.stringToDate(newsData.publishedAt)?.let {
                Text(
                    text = it.getTimeAgo(),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = newsData.title,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun TopNewsPreview() {
    NewsAppTheme {
        TopNewsScreen(rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun TopNewsItemPreview() {
    NewsAppTheme {
        TopNewsItem(
            newsData = NewsData(
                id = 1,
                author = "John Doe",
                imageUrl = R.drawable.news,
                title = "Title 1",
                description = "Description 1",
                publishedAt = "2021-10-01"
            )
        )
    }
}
