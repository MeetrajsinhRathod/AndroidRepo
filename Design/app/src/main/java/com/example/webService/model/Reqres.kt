package com.example.webService.model

import com.google.gson.annotations.SerializedName

data class GetUserDataResponse (

    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val data: List<UserData>,
)

data class UserData (

    val id: Int,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val avatar: String
)

data class LoginRequest(

    val email: String,
    val password: String
)

data class  LoginResponse(

    val token: String
)

data class UpdateUserDataRequest(
    val name: String,
    val job: String
)

data class UpdateUserDataResponse(
    val name: String,
    val job: String,
    val updatedAt: String,
)