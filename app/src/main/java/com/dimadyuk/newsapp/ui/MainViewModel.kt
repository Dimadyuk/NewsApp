package com.dimadyuk.newsapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dimadyuk.newsapp.MainApp
import com.dimadyuk.newsapp.data.model.ArticleCategory
import com.dimadyuk.newsapp.data.model.TopNewsResponse
import com.dimadyuk.newsapp.data.model.getArticleCategory
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
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

    private val _searchedNewsResponse: MutableStateFlow<TopNewsResponse?> = MutableStateFlow(null)
    val searchedNewsResponse: StateFlow<TopNewsResponse?>
        get() = _searchedNewsResponse

    private val _selectedCategory: MutableStateFlow<ArticleCategory?> = MutableStateFlow(null)
    val selectedCategory: StateFlow<ArticleCategory?>
        get() = _selectedCategory

    private val _query: MutableStateFlow<String> = MutableStateFlow("")
    val query: StateFlow<String>
        get() = _query

    var sourceName = MutableStateFlow("abc-news")

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean>
        get() = _isError

    private val exceptionHandler = CoroutineExceptionHandler { _, error ->
        if (error is Exception) {
            _isError.value = true
            _isLoading.value = false
        }
    }
    fun getTopArticles() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            _newsResponse.value = repository.getArticles()
            dataIsLoaded()
        }
    }

    fun getArticlesByCategory(category: String) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            _getArticleByCategory.value = repository.getArticlesByCategory(category)
            dataIsLoaded()
        }
    }

    fun getArticlesBySources() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            _getArticleBySource.value = repository.getArticlesBySources(sourceName.value)
            dataIsLoaded()
        }
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getArticleCategory(category)
        _selectedCategory.value = newCategory
    }

    fun getArticlesByQuery() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            _searchedNewsResponse.value = repository.getArticlesByQuery(query.value)
            dataIsLoaded()
        }
    }

    fun onQueryChanged(query: String) {
        if (query.isBlank()) {
            _searchedNewsResponse.value = null
        }
        _query.value = query
    }

    private fun dataIsLoaded() {
        _isLoading.value = false
        _isError.value = false
    }
}