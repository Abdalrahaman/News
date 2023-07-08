package com.example.task1.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.task1.data.Result
import androidx.lifecycle.viewModelScope
import com.example.task1.data.model.Article
import com.example.task1.data.repository.DefaultRepository
import com.example.task1.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DefaultRepository
) : BaseViewModel() {

    private val _topHeadlinesResult = MutableLiveData<List<Article>>()
    val topHeadlinesResult: LiveData<List<Article>>
        get() = _topHeadlinesResult

    private val _newsResult = MutableLiveData<List<Article>>()
    val newsResult: LiveData<List<Article>>
        get() = _newsResult

    fun getTopHeadlines() = viewModelScope.launch {
        showLoading.postValue(true)
        val result = repository.getTopHeadlines()
        showLoading.postValue(false)
        when (result) {
            is Result.Success -> {
                result.data.let {
                    _topHeadlinesResult.value = it.articles
                }
            }

            is Result.Error -> {
                showToast.postValue(result.message)
            }

            else -> {
                Log.e("HomeViewModel", "Result not type found to handle")
            }
        }
    }

    fun getNews() = viewModelScope.launch {
        showLoading.postValue(true)
        val result = repository.getNews()
        showLoading.postValue(false)
        when (result) {
            is Result.Success -> {
                result.data.let {
                    _newsResult.value = it.articles
                }
            }

            is Result.Error -> {
                showToast.postValue(result.message)
            }

            else -> {
                Log.e("HomeViewModel", "Result not type found to handle")
            }
        }
    }
}