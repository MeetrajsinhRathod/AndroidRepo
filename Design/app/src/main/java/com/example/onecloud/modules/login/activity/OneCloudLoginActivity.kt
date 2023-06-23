package com.example.onecloud.modules.login.activity

import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.design.R
import com.example.design.databinding.ActivityOneCloudLoginBinding
import com.example.onecloud.base.BaseActivity
import com.example.onecloud.modules.login.model.OneCloudUserLoginRequest
import com.example.onecloud.modules.login.model.OneCloudUserLoginResponse
import com.example.onecloud.modules.login.viewModel.LoginViewModel
import com.google.android.material.textfield.TextInputLayout

class OneCloudLoginActivity : BaseActivity<ActivityOneCloudLoginBinding, LoginViewModel>() {

    override fun getResId(): Int = R.layout.activity_one_cloud_login
    override fun setViewModel(): LoginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

    override fun setUpView() {
        binding.etEmail.doOnTextChanged { text, _, _, _ ->
            setHelperTextForTextInputLayout(text, binding.etEmailLayout)
        }
        binding.etPassword.doOnTextChanged { text, _, _, _ ->
            setHelperTextForTextInputLayout(text, binding.etPasswordLayout)
        }
        setResponseObserver()
        observeNavigation()
    }

    private fun observeNavigation() {
        viewModel.navigateTo.observe(this) {
            when (it) {
                LoginViewModel.NavigationEvent.GoBack -> finish()
                LoginViewModel.NavigationEvent.NavigateToDashboard -> callLoginApi()
            }
        }
    }

    private fun callLoginApi() {
        binding.btnLogin.startAnimation()
        if (!binding.etEmail.text.isNullOrEmpty() && !binding.etPassword.text.isNullOrEmpty()) {
            val loginRequest = OneCloudUserLoginRequest(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
            viewModel.logInUser(loginRequest)
        }
    }

    private fun setResponseObserver() {
        viewModel.loginResponse.observe(this) {
            if (it != null) { saveUserData(it) } else { showError("LogIn Failed Try again") }
            binding.btnLogin.revertAnimation()
        }
    }

    private fun saveUserData(oneCloudUserLoginResponse: OneCloudUserLoginResponse) {
        val sharedPreferences = this.getSharedPreferences("application", MODE_PRIVATE)
        sharedPreferences.edit().apply{
            putString("userToken", oneCloudUserLoginResponse.data[0].token)
            putBoolean("isUserLoggedIn", true)
            apply()
        }
    }

    private fun setHelperTextForTextInputLayout(text: CharSequence?, textInputLayout: TextInputLayout) {
        if (text?.length == 0) {
            textInputLayout.helperText = "This field is required"
        } else {
            textInputLayout.helperText = null
        }
    }
}