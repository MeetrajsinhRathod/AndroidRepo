package com.example.onecloud.modules.login.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.onecloud.api.RetrofitObject
import com.example.onecloud.base.BaseErrorResponse
import com.example.onecloud.base.BaseViewModel
import com.example.onecloud.modules.login.model.OneCloudUserLoginRequest
import com.example.onecloud.modules.login.model.OneCloudUserLoginResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel: BaseViewModel() {

    var loginResponse = MutableLiveData<OneCloudUserLoginResponse>()
    var errorResponse = MutableLiveData<BaseErrorResponse>()

    sealed class NavigationEvent {

        object GoBack: NavigationEvent()

        object NavigateToDashboard: NavigationEvent()
    }

    private val _navigateTo = MutableLiveData<NavigationEvent>()
    val navigateTo: LiveData<NavigationEvent>
        get() = _navigateTo

    fun logInUser(loginRequest: OneCloudUserLoginRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiService.logUserIn(loginRequest)
            if (response.code() == 200) {
                loginResponse.postValue(response.body())
            } else {
                val error = Gson().fromJson(response.errorBody()?.string(), BaseErrorResponse::class.java)
                errorResponse.postValue(error)
            }
        }
    }

    fun goBack() {
        _navigateTo.postValue(NavigationEvent.GoBack)
    }

    fun navigateToDashboard() {
        _navigateTo.postValue(NavigationEvent.NavigateToDashboard)
    }
}