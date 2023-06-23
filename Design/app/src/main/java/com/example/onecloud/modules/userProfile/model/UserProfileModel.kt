package com.example.onecloud.modules.userProfile.model

import com.google.gson.annotations.SerializedName

data class UserProfileResponse(
    val status: String,
    val data: ArrayList<ProfileData>
)

data class ProfileData(
    @SerializedName("display_name")
    var displayName: String,

    @SerializedName("first_name")
    var firstName: String,

    @SerializedName("last_name")
    var lastName: String,

    @SerializedName("username")
    var username: String,

    @SerializedName("email")
    var email: String,

    @SerializedName("avatar")
    var avatar: String,

    @SerializedName("is_mfa")
    var isMfa: Boolean,

    @SerializedName("is_skip_mfa")
    var isSkipMfa: Boolean,

    @SerializedName("status")
    var status: String,
)

data class StatusResponse(
    val status: String,
    val data: StatusData,
    val message: String
)
data class StatusData(
    @SerializedName("status")
    var status: String,

    @SerializedName("is_show_in_chat")
    var isShowInChat: Boolean = false,

    @SerializedName("expiry")
    var expiry: String = "2023-06-24T06:52:11.241Z"
)