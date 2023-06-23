package com.example.onecloud.modules.login.activity

import androidx.lifecycle.ViewModelProvider
import com.example.design.R
import com.example.design.databinding.ActivityOneCloudOnboardBinding
import com.example.onecloud.base.BaseActivity
import com.example.onecloud.modules.login.viewModel.LoginViewModel
import com.example.onecloud.modules.login.viewModel.OnboardingViewModel

class OneCloudOnBoardActivity : BaseActivity<ActivityOneCloudOnboardBinding, OnboardingViewModel>() {

    override fun setViewModel(): OnboardingViewModel = ViewModelProvider(this)[OnboardingViewModel::class.java]

    override fun getResId(): Int = R.layout.activity_one_cloud_onboard

    override fun setUpView() {
        observeNavigation()
    }

    private fun observeNavigation() {
        viewModel.navigateTo.observe(this) {
            when (it) {
                OnboardingViewModel.NavigationEvent.GoBack -> finish()
                OnboardingViewModel.NavigationEvent.NavigateToLogin -> openLoginScreen()
            }
        }
    }

    private fun openLoginScreen() {
        launchActivity<OneCloudLoginActivity>()
        finish()
    }
}