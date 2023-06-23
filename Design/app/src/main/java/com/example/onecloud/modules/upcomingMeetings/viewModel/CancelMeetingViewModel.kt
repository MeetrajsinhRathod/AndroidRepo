package com.example.onecloud.modules.upcomingMeetings.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.onecloud.base.BaseErrorResponse
import com.example.onecloud.base.BaseViewModel
import com.example.onecloud.modules.upcomingMeetings.model.CancelMeetingRequest
import com.example.webService.api.ResponseType
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CancelMeetingViewModel: BaseViewModel() {

    private val _meetingResponse = MutableLiveData<ResponseType>()
    val meetingResponse: LiveData<ResponseType>
        get() = _meetingResponse

    var errorResponse = MutableLiveData<BaseErrorResponse>()

    fun cancelMeeting(id: Int, description: String) {
        val cancelMeetingRequest = CancelMeetingRequest(description)
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiService.cancelScheduleMeeting(id, cancelMeetingRequest)
            if (response.isSuccessful) {
                _meetingResponse.postValue(ResponseType.Success)
            } else {
                val error = Gson().fromJson(response.errorBody()?.string(), BaseErrorResponse::class.java)
                errorResponse.postValue(error)
            }
        }
    }
}