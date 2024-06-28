package com.dimadyuk.newsapp.network

import com.dimadyuk.newsapp.data.model.TopNewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    suspend fun getTopArticles(
        @Query("country") country: String = "us",
    ): TopNewsResponse

    @GET("top-headlines")
    suspend fun getArticlesByCategory(
        @Query("category") category: String,
    ): TopNewsResponse

    @GET("everything")
    suspend fun getArticlesBySources(
        @Query("sources") source: String,
    ): TopNewsResponse
}