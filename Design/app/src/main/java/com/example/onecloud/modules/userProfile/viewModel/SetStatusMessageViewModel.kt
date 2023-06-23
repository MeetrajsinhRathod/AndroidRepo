package com.example.onecloud.modules.userProfile.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onecloud.api.RetrofitObject
import com.example.onecloud.modules.userProfile.model.StatusData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SetStatusMessageViewModel: ViewModel() {

    private val _statusSuccessResponse = MutableLiveData<StatusData?>()
    val statusSuccessResponse: LiveData<StatusData?>
        get() = _statusSuccessResponse

    fun setStatusInfo(userId: String, token: String, statusData: StatusData) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitObject.apiService.setStatus(userId, statusData)
            if (response.code() == 200) {
                _statusSuccessResponse.postValue(response.body()?.data)
            } else {
                _statusSuccessResponse.postValue(null)
            }
        }
    }
}