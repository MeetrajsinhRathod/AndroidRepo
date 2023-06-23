package com.example.onecloud.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {

    private const val baseUrl = "https://sandbox-api.ocmeet.us/api/"

    val oneCloudApi: OneCloudApiInterface by lazy {
        Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OneCloudApiInterface::class.java)
    }
}