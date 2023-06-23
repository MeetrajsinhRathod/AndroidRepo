package com.example.onecloud.modules.login.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.onecloud.api.RetrofitObject
import com.example.onecloud.base.BaseViewModel
import com.example.onecloud.modules.login.model.OneCloudUserLoginRequest
import com.example.onecloud.modules.login.model.OneCloudUserLoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel: BaseViewModel() {

    var loginResponse = MutableLiveData<OneCloudUserLoginResponse?>()

    sealed class NavigationEvent {

        object GoBack: NavigationEvent()

        object NavigateToDashboard: NavigationEvent()
    }

    private val _navigateTo = MutableLiveData<LoginViewModel.NavigationEvent>()
    val navigateTo: LiveData<LoginViewModel.NavigationEvent>
        get() = _navigateTo

    fun logInUser(loginRequest: OneCloudUserLoginRequest) {
        GlobalScope.launch(Dispatchers.IO) {
            val response = RetrofitObject.oneCloudApi.logUserIn(loginRequest)
            if (response.code() == 200) {
                loginResponse.postValue(response.body())
            } else { loginResponse.postValue(null) }
        }
    }

    fun goBack() {
        _navigateTo.postValue(NavigationEvent.GoBack)
    }

    fun navigateToDashboard() {
        _navigateTo.postValue(NavigationEvent.NavigateToDashboard)
    }
}