package com.dimadyuk.newsapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dimadyuk.newsapp.MainApp
import com.dimadyuk.newsapp.data.model.ArticleCategory
import com.dimadyuk.newsapp.data.model.TopNewsResponse
import com.dimadyuk.newsapp.data.model.getArticleCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = getApplication<MainApp>().repository

    private val _newsResponse = MutableStateFlow(TopNewsResponse())
    val newsResponse: StateFlow<TopNewsResponse>
        get() = _newsResponse

    private val _getArticleByCategory = MutableStateFlow(TopNewsResponse())
    val getArticleByCategory: StateFlow<TopNewsResponse>
        get() = _getArticleByCategory

    private val _getArticleBySource = MutableStateFlow(TopNewsResponse())
    val getArticleBySource: StateFlow<TopNewsResponse>
        get() = _getArticleBySource

    private val _selectedCategory: MutableStateFlow<ArticleCategory?> = MutableStateFlow(null)
    val selectedCategory: StateFlow<ArticleCategory?>
        get() = _selectedCategory

    var sourceName = MutableStateFlow("abc-news")

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    fun getTopArticles() {
        _isLoading.value = true
        viewModelScope.launch {
            _newsResponse.value = repository.getArticles()
            _isLoading.value = false
        }
    }

    fun getArticlesByCategory(category: String) {
        _isLoading.value = true
        viewModelScope.launch {
            _getArticleByCategory.value = repository.getArticlesByCategory(category)
            _isLoading.value = false
        }
    }

    fun getArticlesBySources() {
        _isLoading.value = true
        viewModelScope.launch {
            _getArticleBySource.value = repository.getArticlesBySources(sourceName.value)
            _isLoading.value = false
        }
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getArticleCategory(category)
        _selectedCategory.value = newCategory
    }
}