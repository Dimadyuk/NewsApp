package com.dimadyuk.newsapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.dimadyuk.newsapp.R
import com.dimadyuk.newsapp.data.model.TopNewsArticle
import com.dimadyuk.newsapp.ui.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SourcesScreen(
    viewModel: MainViewModel
) {
    val list = listOf(
        "TechCrunch" to "techcrunch",
        "TalkSport" to "talksport",
        "Business Insider" to "business-insider",
        "Reuters" to "reuters",
        "Politico" to "politico",
        "TheVerge" to "the-verge",
    )
    val sourceName = viewModel.sourceName.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = sourceName + " Source")
                },
                actions = {
                    var menuExpanded by remember {
                        mutableStateOf(false)
                    }
                    IconButton(onClick = {
                        menuExpanded = true
                    }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                    }
                    MaterialTheme(
                        shapes = MaterialTheme.shapes.copy(
                            medium = RoundedCornerShape(16.dp)
                        )
                    ) {
                        DropdownMenu(
                            expanded = menuExpanded,
                            onDismissRequest = {
                                menuExpanded = false
                            },
                        ) {
                            list.forEach {
                                DropdownMenuItem(
                                    text = {
                                        Text(text = it.first)
                                    },
                                    onClick = {
                                        viewModel.sourceName.value = it.second
                                        menuExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            )
        }
    ) { padding ->
        viewModel.getArticlesBySources()
        val articles = viewModel.getArticleBySource.collectAsState().value
        SourceContent(
            articles = articles.articles ?: emptyList(),
            paddingValue = padding
        )
    }
}

@Composable
fun SourceContent(
    articles: List<TopNewsArticle>,
    paddingValue: PaddingValues
) {

    val uriHandler = LocalUriHandler.current

    LazyColumn(
        modifier = Modifier.padding(paddingValue),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(articles.size) { index ->
            val article = articles[index]
            val annotatedString = buildAnnotatedString {
                pushStringAnnotation(
                    tag = "URL",
                    annotation = article.url ?: "newsapi.org"
                )
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline
                    ),
                ) {
                    append("Read Full Article Here")
                }
            }
            Card(
                modifier = Modifier
                    .padding(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.purple_700),
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp,
                ),
            ) {
                Column(
                    modifier = Modifier
                        .height(200.dp)
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = article.title ?: "Not Available",
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.white),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = article.description ?: "Not Available",
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.white),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.white),
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 6.dp,
                        ),
                        onClick = {
                            annotatedString
                                .getStringAnnotations(
                                    0,
                                    annotatedString.length
                                )
                                .firstOrNull()?.let { result ->
                                    if (result.tag == "URL") {
                                        uriHandler.openUri(result.item)
                                    }
                                }
                        }) {
                        Text(text = annotatedString)
                    }
                }
            }
        }
    }
}
