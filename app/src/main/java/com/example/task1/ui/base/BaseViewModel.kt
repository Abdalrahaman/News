package com.example.task1.ui.base

import androidx.lifecycle.ViewModel
import com.example.task1.ui.helper.NavigationCommand
import com.example.task1.ui.helper.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {

    val navigationCommand: SingleLiveEvent<NavigationCommand> = SingleLiveEvent()
    val showToast: SingleLiveEvent<String> = SingleLiveEvent()
    val showLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
}