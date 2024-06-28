package com.dimadyuk.newsapp.data

import com.dimadyuk.newsapp.network.NewsManager

class Repository(
    private val manager: NewsManager
) {
    suspend fun getArticles() = manager.getArticles("us")
    suspend fun getArticlesByCategory(category: String) = manager.getArticlesByCategory(category)
    suspend fun getArticlesBySources(source: String) = manager.getArticlesBySource(source = source)
}