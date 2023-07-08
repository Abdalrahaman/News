package com.example.task1.ui.viewmodels

import com.example.task1.data.repository.DefaultRepository
import com.example.task1.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: DefaultRepository
) : BaseViewModel() {
}