package com.example.task1.data.source

import com.example.task1.data.Result
import com.example.task1.data.model.NewsResponse

interface NewsDataSource {
    suspend fun getTopHeadlines(): Result<NewsResponse>
    suspend fun getNews(): Result<NewsResponse>
}