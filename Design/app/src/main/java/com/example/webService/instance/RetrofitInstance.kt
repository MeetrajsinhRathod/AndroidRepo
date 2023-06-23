package com.example.webService.instance

import com.example.webService.api.ReqresApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val reqresApi: ReqresApi by lazy {
        Retrofit.Builder().baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReqresApi::class.java)
    }
}