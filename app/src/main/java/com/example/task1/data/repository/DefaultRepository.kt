package com.example.task1.data.repository

import com.example.task1.data.Result
import com.example.task1.data.model.NewsResponse
import com.example.task1.data.source.NewsDataSource
import com.example.task1.data.source.remote.ApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val remoteDataSource: ApiService
) : NewsDataSource {
    override suspend fun getTopHeadlines(): Result<NewsResponse> {
        return try {
            Result.Success(remoteDataSource.getTopHeadlines())
        } catch (ex: IOException) {
            Result.Error(ex.localizedMessage)
        } catch (ex: HttpException) {
            Result.Error("SomeThing Error")
        }
    }

    override suspend fun getNews(): Result<NewsResponse> {
        return try {
            Result.Success(remoteDataSource.getNews())
        } catch (ex: IOException) {
            Result.Error(ex.localizedMessage)
        } catch (ex: HttpException) {
            Result.Error("SomeThing Error")
        }
    }

}