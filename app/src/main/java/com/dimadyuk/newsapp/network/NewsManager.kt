package com.dimadyuk.newsapp.network

import com.dimadyuk.newsapp.data.model.TopNewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsManager(
    private val service: NewsService
) {

    suspend fun getArticles(country: String): TopNewsResponse = withContext(Dispatchers.IO) {
        service.getTopArticles(country = country)
    }

    suspend fun getArticlesByCategory(category: String): TopNewsResponse = withContext(Dispatchers.IO) {
        service.getArticlesByCategory(category = category)
    }

    suspend fun getArticlesBySource(source: String): TopNewsResponse = withContext(Dispatchers.IO) {
        service.getArticlesBySources(source = source)
    }

    suspend fun getArticlesByQuery(query: String): TopNewsResponse = withContext(Dispatchers.IO) {
        service.getArticlesByQuery(query = query)
    }
}