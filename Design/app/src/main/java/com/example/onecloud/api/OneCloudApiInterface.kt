package com.example.onecloud.api

import com.example.onecloud.modules.login.model.OneCloudUserLoginRequest
import com.example.onecloud.modules.login.model.OneCloudUserLoginResponse
import com.example.onecloud.modules.upcomingMeetings.model.CancelMeetingRequest
import com.example.onecloud.modules.upcomingMeetings.model.CancelMeetingResponse
import com.example.onecloud.modules.upcomingMeetings.model.UpcomingMeetingsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface OneCloudApiInterface {

    @POST("v1/auth/email-login/")
    suspend fun logUserIn(@Body loginRequest: OneCloudUserLoginRequest): Response<OneCloudUserLoginResponse>

    @GET("v1/meeting/schedule/upcoming_meetings/")
    suspend fun getUpcomingMeetings(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Header("authorization") token: String
    ): Response<UpcomingMeetingsResponse>

    @POST("v1/meeting/schedule/{id}/cancel_schedule_meeting/")
    suspend fun cancelScheduleMeeting(
        @Path("id") id: Int,
        @Header("authorization") token: String,
        @Body cancelMeetingRequest: CancelMeetingRequest,
    ): Response<CancelMeetingResponse>
}