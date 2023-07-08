package com.wecancity.khebra.data.prefs

interface Preference<T> {

    fun save(obj: T)

    fun get(): T?

    fun remove(): Boolean
}
