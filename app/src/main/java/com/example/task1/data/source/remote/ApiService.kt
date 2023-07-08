package com.example.task1.data.source.remote

import com.example.task1.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String = "us"): NewsResponse

    @GET("everything")
    suspend fun getNews(@Query("q") query: String = "apple"): NewsResponse
}