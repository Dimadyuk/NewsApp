package com.dimadyuk.newsapp.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dimadyuk.newsapp.MockData
import com.dimadyuk.newsapp.MockData.getTimeAgo
import com.dimadyuk.newsapp.R
import com.dimadyuk.newsapp.model.TopNewsArticle
import com.dimadyuk.newsapp.model.getAllArticleCategories
import com.dimadyuk.newsapp.network.NewsManager
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun CategoriesScreen(
    onFetchCategory: (String) -> Unit = {},
    newsManager: NewsManager,
) {
    val tabsItems = getAllArticleCategories()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        LazyRow {
            items(tabsItems.size) {
                val category = tabsItems[it]
                CategoryTab(
                    category = category.categoryName,
                    isSelected = newsManager.selectedCategory.value == category,
                    onFetchCategory = onFetchCategory
                )
            }
        }
        ArticleContent(
            articles = newsManager.getArticleByCategory.value.articles ?: emptyList()
        )

    }
}


@Composable
fun CategoryTab(
    category: String,
    isSelected: Boolean = false,
    onFetchCategory: (String) -> Unit
) {
    val background = if (isSelected) {
        colorResource(id = R.color.purple_200)
    } else {
        colorResource(id = R.color.purple_700)
    }

    Surface(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 2.dp)
            .clickable { onFetchCategory(category) },
        shape = MaterialTheme.shapes.small,
        color = background
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun ArticleContent(
    articles: List<TopNewsArticle>
) {
    LazyColumn {
        items(articles.size) { index ->
            ArticleItem(article = articles[index])
        }
    }
}

@Composable
fun ArticleItem(article: TopNewsArticle) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        border = BorderStroke(2.dp, colorResource(id = R.color.purple_200))
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            CoilImage(
                imageModel = article.urlToImage,
                contentDescription = article.title,
                modifier = Modifier
                    .size(100.dp),
                placeHolder = ImageBitmap.imageResource(id = R.drawable.news),
                error = ImageBitmap.imageResource(id = R.drawable.news),
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = article.title ?: "Not available",
                    fontWeight = FontWeight.Bold,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = article.author ?: "Not available",
                        fontWeight = FontWeight.Normal,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    article.publishedAt?.let {
                        Text(
                            text = MockData.stringToDate(
                                article.publishedAt
                            )?.getTimeAgo() ?: "Not available",
                            fontWeight = FontWeight.Normal,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticlePreview() {
    ArticleContent(
        articles = listOf(
            TopNewsArticle(
                author = "John Doe",
                title = "Title 1",
                description = "Description 1",
                urlToImage = "https://via.placeholder.com/150",
                publishedAt = "2024-10-01"
            ),
            TopNewsArticle(
                author = "Jane Doe",
                title = "Title 2",
                description = "Description 2",
                urlToImage = "https://via.placeholder.com/150",
                publishedAt = "2024-10-02"
            ),
        )
    )
}
