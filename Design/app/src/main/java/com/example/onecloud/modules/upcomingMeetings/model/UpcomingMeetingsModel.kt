package com.example.onecloud.modules.upcomingMeetings.model

import com.google.gson.annotations.SerializedName

sealed class MeetingsListSealedClass {
    data class MeetingDate(
        val date: String
    ): MeetingsListSealedClass()
}

data class UpcomingMeetingsResponse(
    val status: Long,
    val data: UpcomingMeetingsData,
    val message: String,
)

data class UpcomingMeetingsData(
    val count: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("current_page")
    val currentPage: Int,
    val results: ArrayList<UpcomingMeetingsDataResult>,
)

data class UpcomingMeetingsDataResult(
    @SerializedName("occurrence_id")
    val occurrenceId: String,
    val date: String,
    val schedule: Schedule,
    val source: String,
    val user: UpcomingMeetingsDataResultUser,
): MeetingsListSealedClass()

data class Schedule(
    val pk: Int,
    val title: String,
    val description: String,
    @SerializedName("reminder_time")
    val reminderTime: Long,
    val timezone: String,
    @SerializedName("start_time")
    val startTime: String,
    val duration: String,
    @SerializedName("meeting_type")
    val meetingType: String,
    val passcode: String,
    @SerializedName("recurrence_type")
    val recurrenceType: String,
    val participants: List<Any?>,
    @SerializedName("optional_participants")
    val optionalParticipants: List<Any?>,
    val moderators: List<Any?>,
    @SerializedName("recurrence_data")
    val recurrenceData: RecurrenceData,
    val user: String,
    @SerializedName("company_logo")
    val companyLogo: Any?,
    @SerializedName("room_id")
    val roomId: String,
    @SerializedName("enable_chat")
    val enableChat: Boolean,
    @SerializedName("facial_expression")
    val facialExpression: Boolean,
    @SerializedName("audio_only")
    val audioOnly: Boolean,
    @SerializedName("record_meeting")
    val recordMeeting: Boolean,
    val transcribe: Boolean,
    @SerializedName("enable_lobby")
    val enableLobby: Boolean,
    @SerializedName("start_muted")
    val startMuted: Boolean,
    @SerializedName("enable_tile_view")
    val enableTileView: Boolean,
    @SerializedName("require_company_info")
    val requireCompanyInfo: Boolean,
    @SerializedName("require_registration")
    val requireRegistration: Boolean,
    @SerializedName("hide_viewers")
    val hideViewers: Boolean,
    @SerializedName("ics_attachment")
    val icsAttachment: String,
    @SerializedName("unique_room")
    val uniqueRoom: Boolean,
)

data class RecurrenceData(
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("repeat_every")
    val repeatEvery: Long,
)

data class UpcomingMeetingsDataResultUser(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("display_name")
    val displayName: String,
    val email: String,
    val avatar: String,
)

data class CancelMeetingRequest (
    @SerializedName("cancel_description")
    val description: String
    )

data class CancelMeetingResponse (
    val message: String
        )