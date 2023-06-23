package com.example.onecloud.modules.userProfile.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onecloud.api.RetrofitObject
import com.example.onecloud.modules.userProfile.model.ProfileData
import com.example.onecloud.modules.userProfile.model.StatusData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserProfileViewModel: ViewModel() {

    private val _profileSuccessResponse = MutableLiveData<ProfileData?>()
    val profileSuccessResponse: LiveData<ProfileData?>
        get() = _profileSuccessResponse

    private val _statusSuccessResponse = MutableLiveData<StatusData?>()
    val statusSuccessResponse: LiveData<StatusData?>
        get() = _statusSuccessResponse

    fun getProfileInfo() {
            viewModelScope.launch(Dispatchers.IO) {
                val response = RetrofitObject.apiService.getProfileInfo()
                if (response.code() == 200) {
                    _profileSuccessResponse.postValue(response.body()?.data?.get(0))
                } else {
                    _profileSuccessResponse.postValue(null)
                }
            }
    }

    fun getStatusInfo(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
                val response = RetrofitObject.apiService.getStatusInfo(userId)
                if (response.code() == 200) {
                    _statusSuccessResponse.postValue(response.body()?.data)
                } else {
                    _statusSuccessResponse.postValue(null)
                }
            }
    }
}