package com.dimadyuk.newsapp.data.model

import com.dimadyuk.newsapp.R

data class NewsData(
    val id: Int,
    val imageUrl: Int = R.drawable.news,
    val author: String,
    val title: String,
    val description: String,
    val publishedAt: String
)
