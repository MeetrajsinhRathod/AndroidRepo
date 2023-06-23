package com.example.onecloud.modules.upcomingMeetings.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.onecloud.Utills.formatDate
import com.example.onecloud.api.RetrofitObject
import com.example.onecloud.base.BaseErrorResponse
import com.example.onecloud.base.BaseViewModel
import com.example.onecloud.modules.upcomingMeetings.model.MeetingsListSealedClass
import com.example.onecloud.modules.upcomingMeetings.model.UpcomingMeetingsDataResult
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpcomingMeetingsViewModel: BaseViewModel() {

    private val meetingList = arrayListOf<MeetingsListSealedClass>()
    private val _meetingResponse = MutableLiveData<ArrayList<MeetingsListSealedClass>>()
    val meetingResponse: LiveData<ArrayList<MeetingsListSealedClass>>
        get() = _meetingResponse
    var errorResponse = MutableLiveData<BaseErrorResponse>()
    var totalPage = MutableLiveData(1)

    fun getUpcomingMeetings(page: Int, limit: Int, token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitObject.apiService.getUpcomingMeetings(page, 5)
            if (response.code() == 200) {
                response.body()?.data?.results?.let { generateDateMeetingsPair(it) }
                _meetingResponse.postValue(meetingList)
                totalPage.postValue(response.body()?.data?.totalPages ?: 1)
            } else {
                val error = Gson().fromJson(response.errorBody()?.string(), BaseErrorResponse::class.java)
                errorResponse.postValue(error)
            }
        }
    }

    fun clearList() {
        meetingList.clear()
    }

    private fun generateDateMeetingsPair(meetingResult: ArrayList<UpcomingMeetingsDataResult>) {
        val map = mutableMapOf <String, ArrayList<UpcomingMeetingsDataResult>> ()
        val generateArray = arrayListOf<MeetingsListSealedClass>()
        meetingResult.forEach {
            val dateString = it.date.formatDate("EEEE, dd, MMMM, yyyy")
            if (map.isEmpty()) {
                map[dateString] = arrayListOf(it)
            }
            if (map.containsKey(dateString)) {
                map[dateString]!!.add(it)
            } else {
                map[dateString] = arrayListOf(it)
            }
        }
        map.forEach {
            generateArray.add(MeetingsListSealedClass.MeetingDate(it.key))
            generateArray.addAll(it.value)
        }
        meetingList.addAll(generateArray)
    }
}