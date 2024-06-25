package com.dimadyuk.newsapp.ui.screen

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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dimadyuk.newsapp.MockData
import com.dimadyuk.newsapp.MockData.getTimeAgo
import com.dimadyuk.newsapp.R
import com.dimadyuk.newsapp.model.TopNewsArticle
import com.dimadyuk.newsapp.ui.theme.NewsAppTheme
import com.skydoves.landscapist.coil.CoilImage


@Composable
fun TopNewsScreen(
    navController: NavController,
    articles: List<TopNewsArticle>
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Top News", fontWeight = FontWeight.SemiBold)
        LazyColumn {
            items(articles.size) { index ->
                TopNewsItem(
                    article = articles[index],
                    onNewsClick = {
                        navController.navigate("DetailScreen/$index")
                    }
                )
            }
        }
    }
}

@Composable
fun TopNewsItem(
    article: TopNewsArticle,
    onNewsClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
            .clickable {
                onNewsClick()
            }
    ) {
        CoilImage(
            imageModel = article.urlToImage,
            contentScale = ContentScale.Crop,
            error = ImageBitmap.imageResource(id = R.drawable.news),
            placeHolder = ImageBitmap.imageResource(id = R.drawable.news),
        )
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            MockData.stringToDate(article.publishedAt.orEmpty())?.let {
                Text(
                    text = it.getTimeAgo(),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = article.title.orEmpty(),
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
        TopNewsScreen(rememberNavController(), listOf())
    }
}

@Preview(showBackground = true)
@Composable
fun TopNewsItemPreview() {
    NewsAppTheme {
        TopNewsItem(
            article = TopNewsArticle(
                source = null,
                author = "author",
                title = "title",
                description = "description",
                url = "url",
                urlToImage = "urlToImage",
                publishedAt = "publishedAt",
                content = "content"
            )
        )
    }
}
