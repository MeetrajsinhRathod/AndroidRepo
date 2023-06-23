package com.example.onecloud.modules.login.model

data class OneCloudUserLoginRequest(
    val email: String,
    val password: String
)

data class OneCloudUserLoginResponse(
    val message: String,
    val data: List<User>
)

data class User (
    val token: String,
    val is_mfa: Boolean,
    val user: String,
    val user_email: String,
    val displayName: String,
    val department: String,
    val login: String,
    val refresh_token: String,
    val first_name: String,
    val last_name: String,
    val email: String,
    val username: String
)