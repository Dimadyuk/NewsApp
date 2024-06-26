package com.dimadyuk.newsapp.model

enum class ArticleCategory(
    val categoryName: String
) {
    BUSINESS("business"),
    ENTERTAINMENT("entertainment"),
    GENERAL("general"),
    HEALTH("health"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology")
}

fun getAllArticleCategories(): List<ArticleCategory> {
    return ArticleCategory.entries
}

fun getArticleCategories(category: String): ArticleCategory? {
    return ArticleCategory.entries.find { it.categoryName == category }
}
