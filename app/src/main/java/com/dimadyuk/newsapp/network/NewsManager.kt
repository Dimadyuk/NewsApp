package com.dimadyuk.newsapp.network

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.dimadyuk.newsapp.model.ArticleCategory
import com.dimadyuk.newsapp.model.TopNewsResponse
import com.dimadyuk.newsapp.model.getArticleCategories
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsManager {
    private val _newsResponse = mutableStateOf(TopNewsResponse())
    val newsResponse: State<TopNewsResponse>
        @Composable get() = remember {
            _newsResponse
        }
    private val _getArticleByCategory = mutableStateOf(TopNewsResponse())
    val getArticleByCategory: State<TopNewsResponse>
        @Composable get() = remember {
            _getArticleByCategory
        }

    val selectedCategory: MutableState<ArticleCategory?> = mutableStateOf(null)

    init {
        getArticles()
    }

    private fun getArticles() {
        val service = Api.retrofitService.getTopArticles(country = "us")
        service.enqueue(object : Callback<TopNewsResponse> {
            override fun onResponse(call: Call<TopNewsResponse>, response: Response<TopNewsResponse>) {
                if (response.isSuccessful) {
                    _newsResponse.value = response.body()!!
                    Log.d("news", "${response.body()}")
                } else {
                    Log.d("error", "${response.errorBody()}")
                }

            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                Log.d("error", "${t.printStackTrace()}")
            }

        })
    }

    fun getArticlesByCategory(category: String) {
        val service = Api.retrofitService.getArticlesByCategory(category = category)
        service.enqueue(object : Callback<TopNewsResponse> {
            override fun onResponse(call: Call<TopNewsResponse>, response: Response<TopNewsResponse>) {
                if (response.isSuccessful) {
                    _getArticleByCategory.value = response.body()!!
                    Log.d("category", "${response.body()}")
                } else {
                    Log.d("error", "${response.errorBody()}")
                }

            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                Log.d("error", "${t.printStackTrace()}")
            }

        })
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getArticleCategories(category)
        selectedCategory.value = newCategory
    }
}