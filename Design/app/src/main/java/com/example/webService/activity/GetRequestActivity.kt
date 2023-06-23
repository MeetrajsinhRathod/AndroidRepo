package com.example.webService.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.design.databinding.ActivityGetRequestBinding
import com.example.design.helper.hideKeyboardOnTouchOutside
import com.example.webService.adapter.UserDataAdapter
import com.example.webService.api.WebServiceType
import com.example.webService.viewModel.GetUserDataViewModel
import com.example.webService.viewModel.PostRequestViewModel

class GetRequestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetRequestBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[GetUserDataViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userdataAdapter = UserDataAdapter()
        binding.rvUserData.adapter = userdataAdapter
        viewModel.getUserDataList().observe(this) {
            if (it != null) {
                userdataAdapter.userData = it
                userdataAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Error: Unable To Load data", Toast.LENGTH_SHORT).show()
            }
        }
        getUserData()
    }

    private fun getUserData() {
        when (intent.getSerializableExtra("WebServiceType") as? WebServiceType) {
            WebServiceType.Retrofit -> viewModel.callGetUserApiUsingRetrofit()
            WebServiceType.HttpURLConnection -> viewModel.callGetUserApiUsingHttpURLConnection()
            else -> { viewModel.callGetUserApiUsingOkHttp3() }
        }
    }
}