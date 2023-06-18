package com.example.webService.api

import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

class OkHttp3Base(
    private val requestType: HttpRequestEnum
) {
    val client = OkHttpClient()

    fun callApi(jsonString: String?): Request {
        val requestBody = jsonString?.let { RequestBody.create(MediaType.get("application/json"), it) }
        return Request.Builder()
            .method(requestType.getRequestMethod(), requestBody)
            .url(requestType.getTargetUrl())
            .build()
    }
}