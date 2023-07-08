package com.example.task1.data.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.task1.utils.Constants.Companion.SHARED_PREFERENCE_DEFAULT_NAME
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferencesHelper @Inject constructor(@ApplicationContext val context: Context) {

    private val sharedPreferences: SharedPreferences
    private val gson: Gson = Gson()
    private val editor: SharedPreferences.Editor

    init {
        val masterKey = MasterKey
            .Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        sharedPreferences = EncryptedSharedPreferences.create(
            context,
            SHARED_PREFERENCE_DEFAULT_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        editor = sharedPreferences.edit()
    }

    fun putInt(key: String, value: Int) {
        with(editor) {
            putInt(key, value)
            commit()
        }
    }

    fun getInt(key: String): Int = sharedPreferences.getInt(key, 0)

    fun putBoolean(key: String, value: Boolean) {
        with(editor) {
            putBoolean(key, value)
            commit()
        }
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean =
        sharedPreferences.getBoolean(key, defaultValue)

    fun getBooleanFalse(key: String): Boolean = sharedPreferences.getBoolean(key, false)

    fun putString(key: String, value: String) {
        with(editor) {
            putString(key, value)
            commit()
        }
    }

    fun getString(key: String): String? = sharedPreferences.getString(key, "")

    fun getString(key: String, defaultValue: String): String? =
        sharedPreferences.getString(key, defaultValue)


    fun remove(key: String) = editor.remove(key).commit()

    fun putObject(key: String, `object`: Any) {
        with(editor) {
            putString(key, gson.toJson(`object`))
            commit()
        }
    }

    fun <T> getObject(key: String, a: Class<T>): T? {
        val json = sharedPreferences.getString(key, null)
        return if (json == null) {
            null
        } else {
            try {
                gson.fromJson(json, a)
            } catch (e: Exception) {
                throw IllegalArgumentException(
                    "Object stored with key "
                            + key + " is instance of other class"
                )
            }

        }
    }
}
