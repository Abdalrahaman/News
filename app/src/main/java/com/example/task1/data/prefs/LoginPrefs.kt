package com.example.task1.data.prefs

import com.example.task1.utils.Constants.Companion.LOGIN
import com.wecancity.khebra.data.prefs.Preference

import javax.inject.Inject

class LoginPrefs @Inject constructor(private val preferencesHelper: PreferencesHelper) :
    Preference<Boolean> {
    override fun save(obj: Boolean) = preferencesHelper.putBoolean(LOGIN, obj)

    override fun get(): Boolean =
        preferencesHelper.getBoolean(LOGIN, false)

    override fun remove(): Boolean = preferencesHelper.remove(LOGIN)
}