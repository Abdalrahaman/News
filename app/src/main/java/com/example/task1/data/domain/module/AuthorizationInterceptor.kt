package com.example.task1.data.domain.module

import com.example.task1.utils.Constants.Companion.API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor() : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder().header("x-api-key", API_KEY).build()
        )
    }
}