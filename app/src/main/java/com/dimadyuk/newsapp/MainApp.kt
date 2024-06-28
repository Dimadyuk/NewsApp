package com.dimadyuk.newsapp

import android.app.Application
import com.dimadyuk.newsapp.data.Repository
import com.dimadyuk.newsapp.network.Api
import com.dimadyuk.newsapp.network.NewsManager

class MainApp : Application() {

    private val manager by lazy {
        NewsManager(Api.retrofitService)
    }
    val repository by lazy {
        Repository(manager)
    }
}