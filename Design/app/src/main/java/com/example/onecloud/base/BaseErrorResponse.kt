package com.example.onecloud.base

import com.google.gson.annotations.SerializedName

data class BaseErrorResponse(
    @SerializedName("status")
    var status: Int? = null,

    @SerializedName("data")
    var data: ErrorData? = ErrorData(),

    @SerializedName("message")
    var message: String? = null
)

data class ErrorData(
    @SerializedName("error")
    var error: String? = null
)