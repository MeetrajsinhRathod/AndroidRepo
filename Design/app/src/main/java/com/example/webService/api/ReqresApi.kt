package com.example.webService.api

import com.example.webService.model.GetUserDataResponse
import com.example.webService.model.LoginRequest
import com.example.webService.model.LoginResponse
import com.example.webService.model.UpdateUserDataRequest
import com.example.webService.model.UpdateUserDataResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ReqresApi {

    @GET("users")
    suspend fun getUserData(): Response<GetUserDataResponse>

    @POST("login")
    suspend fun signInUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @PUT("users/{id}")
    suspend fun updateUserDataWithPut(@Body updateUserDataRequest: UpdateUserDataRequest, @Path("id") id: Int): Response<UpdateUserDataResponse>

    @PATCH("users/{id}")
    suspend fun updateUserDataWithPatch(@Body updateUserDataRequest: UpdateUserDataRequest, @Path("id") id: Int): Response<UpdateUserDataResponse>

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<String>
}