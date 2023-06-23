package com.example.onecloud.api

object URLFactory {
    const val baseUrl = "https://sandbox-api.ocmeet.us/api/"
    const val loginPath = "v1/auth/email-login/"
    const val upcomingMeetingsPath = "v1/meeting/schedule/upcoming_meetings/"
    const val cancelMeetingPath = "v1/meeting/schedule/{id}/cancel_schedule_meeting/"
    const val getProfilePath = "v1/auth/profile-information/"
    const val getStatusPath = "v1/auth/user-status/{userId}/"
    const val setStatusPath = "v1/auth/user-status/{userId}/"
}