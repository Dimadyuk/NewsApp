package com.dimadyuk.newsapp

import com.dimadyuk.newsapp.model.NewsData

object MockData {
    val topNewsList = listOf(
        NewsData(
            id = 1,
            author = "John Doe",
            imageUrl = R.drawable.news,
            title = "Title 1",
            description = "The String class represents character strings. All string literals in Kotlin programs, such as \"abc\", are implemented as instances of this class. I’m powered by AI, so surprises and mistakes are possible. Make sure to verify any generated code or suggestions, and share feedback so that we can learn and improve.",
            publishedAt = "2021-10-01"
        ),
        NewsData(
            id = 2,
            author = "Jane Doe",
            imageUrl = R.drawable.earth,
            title = "Title 2",
            description = "Description 2",
            publishedAt = "2021-10-02"
        ),
        NewsData(
            id = 3,
            author = "John Smith",
            imageUrl = R.drawable.man,
            title = "Title 3",
            description = "Description 3",
            publishedAt = "2021-10-03"
        ),
        NewsData(
            id = 4,
            author = "Jane Smith",
            imageUrl = R.drawable.paper,
            title = "Title 4",
            description = "Description 4",
            publishedAt = "2021-10-04"
        ),
        NewsData(
            id = 5,
            author = "John Doe",
            imageUrl = R.drawable.phone,
            title = "Title 5",
            description = "Description 5",
            publishedAt = "2021-10-05"
        ),
        NewsData(
            id = 6,
            author = "Jane Doe",
            imageUrl = R.drawable.news,
            title = "Title 6",
            description = "Description 6",
            publishedAt = "2021-10-06"
        ),
        NewsData(
            id = 7,
            author = "John Smith",
            imageUrl = R.drawable.earth,
            title = "Title 7",
            description = "Description 7",
            publishedAt = "2021-10-07"
        ),
        NewsData(
            id = 8,
            author = "Jane Smith",
            imageUrl = R.drawable.man,
            title = "Title 8",
            description = "Description 8 sjdnfkjs ndfjksdjkff sdkfs dflkmsldfsd lksdfmslfslf",
            publishedAt = "2021-10-08"
        ),
        NewsData(
            id = 9,
            author = "John Doe",
            imageUrl = R.drawable.paper,
            title = "Title 9",
            description = "Description 9",
            publishedAt = "2021-10-09"
        ),
        NewsData(
            id = 10,
            author = "Jane Doe",
            imageUrl = R.drawable.earth,
            title = "Title 10",
            description = "Description 10",
            publishedAt = "2021-10-10"
        ),
        NewsData(
            id = 11,
            author = "John Smith",
            imageUrl = R.drawable.phone,
            title = "Title 11",
            description = "",
            publishedAt = "2021-10-10"
        )
    )

    fun getNews(newsId: Int): NewsData {
        return topNewsList.find { it.id == newsId } ?: topNewsList[0]
    }
}