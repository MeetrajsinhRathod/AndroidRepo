package com.example.onecloud.api

import com.example.onecloud.modules.login.model.OneCloudUserLoginRequest
import com.example.onecloud.modules.login.model.OneCloudUserLoginResponse
import com.example.onecloud.modules.upcomingMeetings.model.CancelMeetingRequest
import com.example.onecloud.modules.upcomingMeetings.model.CancelMeetingResponse
import com.example.onecloud.modules.upcomingMeetings.model.UpcomingMeetingsResponse
import com.example.onecloud.modules.userProfile.model.StatusData
import com.example.onecloud.modules.userProfile.model.StatusResponse
import com.example.onecloud.modules.userProfile.model.UserProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface OneCloudApiInterface {

    @POST("v1/auth/email-login/")
    suspend fun logUserIn(@Body loginRequest: OneCloudUserLoginRequest): Response<OneCloudUserLoginResponse>

    @GET("v1/meeting/schedule/upcoming_meetings/")
    suspend fun getUpcomingMeetings(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<UpcomingMeetingsResponse>

    @POST("v1/meeting/schedule/{id}/cancel_schedule_meeting/")
    suspend fun cancelScheduleMeeting(
        @Path("id") id: Int,
        @Body cancelMeetingRequest: CancelMeetingRequest
    ): Response<CancelMeetingResponse>

    @GET("v1/auth/profile-information/")
    suspend fun getProfileInfo(): Response<UserProfileResponse>

    @GET("v1/auth/user-status/{userId}/")
    suspend fun getStatusInfo(
        @Path("userId") userId: String
    ): Response<StatusResponse>

    @PATCH("v1/auth/user-status/{userId}/")
    suspend fun setStatus(
        @Path("userId") userId: String,
        @Body statusData: StatusData
    ): Response<StatusResponse>
}