package com.dimadyuk.newsapp.network

import com.dimadyuk.newsapp.model.TopNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    fun getTopArticles(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String
    ): Call<TopNewsResponse>

    @GET("top-headlines")
    fun getArticlesByCategory(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): Call<TopNewsResponse>
}