package com.example.onecloud.api

import androidx.appcompat.app.AppCompatActivity
import com.example.AppClass
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val userToken = AppClass.instance.getSharedPreferences(
            "application",
            AppCompatActivity.MODE_PRIVATE
        ).getString("userToken", "")
        val isUserLoggedIn = AppClass.instance.getSharedPreferences(
            "application",
            AppCompatActivity.MODE_PRIVATE
        ).getBoolean("isUserLoggedIn", false)
        if (!userToken.isNullOrEmpty() && isUserLoggedIn) {
            requestBuilder.addHeader("Authorization", "Bearer $userToken")
        }
        requestBuilder.addHeader("Content-Type", "application/json")
        requestBuilder.addHeader("Accept", "application/json")
        return chain.proceed(requestBuilder.build())
    }
}