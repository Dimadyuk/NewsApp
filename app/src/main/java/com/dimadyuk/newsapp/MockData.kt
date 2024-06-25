package com.dimadyuk.newsapp

import com.dimadyuk.newsapp.model.NewsData
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object MockData {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)

    val topNewsList = listOf(
        NewsData(
            id = 1,
            author = "John Doe",
            imageUrl = R.drawable.news,
            title = "Title 1",
            description = "The String class represents character strings. All string literals in Kotlin programs, such as \"abc\", are implemented as instances of this class. I’m powered by AI, so surprises and mistakes are possible. Make sure to verify any generated code or suggestions, and share feedback so that we can learn and improve.",
            publishedAt = dateFormat.format(
                SimpleDateFormat(
                    "yyyy-MM-dd",
                    Locale.ENGLISH
                ).parse("2022-10-01")
            )
        ),
        NewsData(
            id = 2,
            author = "Jane Doe",
            imageUrl = R.drawable.earth,
            title = "Title 2",
            description = "Description 2",
            publishedAt = dateFormat.format(
                SimpleDateFormat(
                    "yyyy-MM-dd",
                    Locale.ENGLISH
                ).parse("2024-10-02")
            )
        ),
        NewsData(
            id = 3,
            author = "John Smith",
            imageUrl = R.drawable.man,
            title = "Title 3",
            description = "Description 3",
            publishedAt = dateFormat.format(
                SimpleDateFormat(
                    "yyyy-MM-dd",
                    Locale.ENGLISH
                ).parse("2024-10-03")
            )
        ),
        NewsData(
            id = 4,
            author = "Jane Smith",
            imageUrl = R.drawable.paper,
            title = "Title 4",
            description = "Description 4",
            publishedAt = dateFormat.format(
                SimpleDateFormat(
                    "yyyy-MM-dd",
                    Locale.ENGLISH
                ).parse("2024-10-04")
            )
        ),
        NewsData(
            id = 5,
            author = "John Doe",
            imageUrl = R.drawable.phone,
            title = "Title 5",
            description = "Description 5",
            publishedAt = dateFormat.format(
                SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2024-10-05")
            )
        ),
        NewsData(
            id = 6,
            author = "Jane Doe",
            imageUrl = R.drawable.news,
            title = "Title 6",
            description = "Description 6",
            publishedAt = dateFormat.format(
                SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2024-10-06")
            )
        ),
        NewsData(
            id = 7,
            author = "John Smith",
            imageUrl = R.drawable.earth,
            title = "Title 7",
            description = "Description 7",
            publishedAt = dateFormat.format(
                SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2024-10-07")
            )
        ),
        NewsData(
            id = 8,
            author = "Jane Smith",
            imageUrl = R.drawable.man,
            title = "Title 8",
            description = "Description 8 sjdnfkjs ndfjksdjkff sdkfs dflkmsldfsd lksdfmslfslf",
            publishedAt = dateFormat.format(
                SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2024-10-08")
            )
        ),
        NewsData(
            id = 9,
            author = "John Doe",
            imageUrl = R.drawable.paper,
            title = "Title 9",
            description = "Description 9",
            publishedAt = dateFormat.format(
                SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2024-10-09")
            )
        ),
        NewsData(
            id = 10,
            author = "Jane Doe",
            imageUrl = R.drawable.earth,
            title = "Title 10",
            description = "Description 10",
            publishedAt = dateFormat.format(
                SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2024-10-10")
            )
        ),
        NewsData(
            id = 11,
            author = "John Smith",
            imageUrl = R.drawable.phone,
            title = "Title 11",
            description = "",
            publishedAt = dateFormat.format(
                SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2024-10-10")
            )
        )
    )


    fun getNews(newsId: Int): NewsData {
        return topNewsList.find { it.id == newsId } ?: topNewsList[0]
    }

    fun Date.getTimeAgo(): String {
        val calendar = Calendar.getInstance()
        calendar.time = this

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)

        val currentCalendar = Calendar.getInstance()

        val currentYear = currentCalendar.get(Calendar.YEAR)
        val currentMonth = currentCalendar.get(Calendar.MONTH)
        val currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH)
        val currentHour = currentCalendar.get(Calendar.HOUR)
        val currentMinute = currentCalendar.get(Calendar.MINUTE)

        return if (year < currentYear) {
            val internal = currentYear - year
            if (internal == 1) {
                "1 year ago"
            } else {
                "$internal years ago"
            }
        } else if (month < currentMonth) {
            val internal = currentMonth - month
            if (internal == 1) {
                "1 month ago"
            } else {
                "$internal months ago"
            }
        } else if (day < currentDay) {
            val internal = currentDay - day
            if (internal == 1) {
                "1 day ago"
            } else {
                "$internal days ago"
            }
        } else if (hour < currentHour) {
            val internal = currentHour - hour
            if (internal == 1) {
                "1 hour ago"
            } else {
                "$internal hours ago"
            }
        } else if (minute < currentMinute) {
            val internal = currentMinute - minute
            if (internal == 1) {
                "1 minute ago"
            } else {
                "$internal minutes ago"
            }
        } else {
            "Just now"
        }
    }

    fun stringToDate(publishedAt: String): Date? {
        return SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss'Z'",
            Locale.ENGLISH
        ).parse(publishedAt)
    }
}
