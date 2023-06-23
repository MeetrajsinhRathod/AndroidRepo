package com.example.onecloud.api

import com.example.onecloud.modules.login.model.OneCloudUserLoginRequest
import com.example.onecloud.modules.login.model.OneCloudUserLoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface OneCloudApiInterface {

    @POST("v1/auth/email-login/")
    suspend fun logUserIn(@Body loginRequest: OneCloudUserLoginRequest): Response<OneCloudUserLoginResponse>
}