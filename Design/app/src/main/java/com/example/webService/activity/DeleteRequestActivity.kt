package com.example.webService.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.design.databinding.ActivityDeleteRequestBinding
import com.example.webService.api.WebServiceType
import com.example.webService.viewModel.DeleteRequestViewModel

class DeleteRequestActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityDeleteRequestBinding
    private lateinit var webServiceType: WebServiceType
    private val viewModel by lazy {
        ViewModelProvider(this)[DeleteRequestViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setResponseObserver()
        binding.btnDeleteUser.setOnClickListener {
            if (binding.etId.text.isNotEmpty()) {
                callDeleteUserApi()
            }
        }
    }

    private fun setResponseObserver() {
        viewModel.getDeleteResponse().observe(this) {
            binding.tvResponse.text = it
        }
    }

    private fun callDeleteUserApi() {
        val userId = binding.etId.text.toString().toInt()
        when (intent.getSerializableExtra("WebServiceType")) {
            WebServiceType.Retrofit -> viewModel.deleteUserWithRetrofit(userId)
            WebServiceType.HttpURLConnection -> viewModel.deleteUserWithHttpUrlConnection(userId)
            else -> { viewModel.deleteUserWithOkHttp3(userId) }
        }
    }
}