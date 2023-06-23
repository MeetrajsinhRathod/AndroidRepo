package com.example.onecloud.modules.login.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.onecloud.base.BaseViewModel

class OnboardingViewModel: BaseViewModel() {

    sealed class NavigationEvent {

        object NavigateToLogin: NavigationEvent()
    }

    private val _navigateTo = MutableLiveData<NavigationEvent>()
    val navigateTo: LiveData<NavigationEvent>
        get() = _navigateTo

    fun navigateToLoginActivity() {
        _navigateTo.postValue(NavigationEvent.NavigateToLogin)
    }
}