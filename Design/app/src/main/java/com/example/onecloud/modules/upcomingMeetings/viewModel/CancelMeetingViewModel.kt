package com.example.onecloud.modules.upcomingMeetings.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onecloud.api.RetrofitObject
import com.example.onecloud.modules.upcomingMeetings.model.CancelMeetingRequest
import com.example.webService.api.ResponseType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CancelMeetingViewModel: ViewModel() {

    private val _meetingResponse = MutableLiveData<ResponseType>()
    val meetingResponse: LiveData<ResponseType>
        get() = _meetingResponse

    fun cancelMeeting(id: Int, userToken: String, description: String) {
        val cancelMeetingRequest = CancelMeetingRequest(description)
        GlobalScope.launch(Dispatchers.IO) {
            val response = RetrofitObject.oneCloudApi.cancelScheduleMeeting(id, userToken,cancelMeetingRequest)
            if (response.isSuccessful) {
                _meetingResponse.postValue(ResponseType.Success)
            } else {
                _meetingResponse.postValue(ResponseType.Failure)
            }
        }
    }
}