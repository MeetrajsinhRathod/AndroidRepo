package com.example.webService.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.lifecycle.ViewModelProvider
import com.example.design.databinding.ActivityPostRequestBinding
import com.example.design.helper.hideKeyboardOnTouchOutside
import com.example.webService.api.WebServiceType
import com.example.webService.model.LoginRequest
import com.example.webService.viewModel.PostRequestViewModel

class PostRequestActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityPostRequestBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[PostRequestViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.btnSignIn.setOnClickListener {
            if (binding.etEmail.text.isNotEmpty() && binding.etPassword.text.isNotEmpty()) {
                callLoginApi()
            }
        }
    }

    //override dispatchTouchEvent function to hide keyboard on touch outside of editText
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            this.hideKeyboardOnTouchOutside()
        }
        return super.dispatchTouchEvent(event)
    }

    private fun callLoginApi() {
        val loginRequest = LoginRequest(binding.etEmail.text.toString(), binding.etPassword.text.toString())
        when (intent.getSerializableExtra("WebServiceType") as? WebServiceType) {
            WebServiceType.Retrofit -> viewModel.signInWithRetrofit(loginRequest)
            WebServiceType.HttpURLConnection -> viewModel.signInWithHttpUrlConnection(loginRequest)
            else -> { viewModel.signInWithOkHttp3(loginRequest) }
        }
    }
}