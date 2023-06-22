package com.example.webService.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.design.databinding.ActivityWebServicesBinding
import com.example.webService.api.HttpRequestEnum
import com.example.webService.api.WebServiceType
import com.google.android.material.tabs.TabLayout

class WebServicesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebServicesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebServicesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.webServiceType = WebServiceType.Retrofit
        setUpView()
    }

    private fun setUpView() {
        binding.btnGetRequest.setOnClickListener {
            val intent = Intent(this, GetRequestActivity::class.java)
            intent.putExtra("WebServiceType", binding.webServiceType)
            startActivity(intent)
        }
        binding.btnPostRequest.setOnClickListener {
            val intent = Intent(this, PostRequestActivity::class.java)
            intent.putExtra("WebServiceType", binding.webServiceType)
            startActivity(intent)
        }
        binding.btnPutRequest.setOnClickListener {
            val intent = Intent(this, UpdateRequestActivity::class.java)
            intent.putExtra("WebServiceType", binding.webServiceType)
            intent.putExtra("RequestType", HttpRequestEnum.PUT())
            startActivity(intent)
        }
        binding.btnPatchRequest.setOnClickListener {
            val intent = Intent(this, UpdateRequestActivity::class.java)
            intent.putExtra("WebServiceType", binding.webServiceType)
            intent.putExtra("RequestType", HttpRequestEnum.PATCH())
            startActivity(intent)
        }
        binding.btnDeleteRequest.setOnClickListener {
            val intent = Intent(this, DeleteRequestActivity::class.java)
            intent.putExtra("WebServiceType", binding.webServiceType)
            startActivity(intent)
        }

        binding.WebServiceTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                setUpButtonText()
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) { }
            override fun onTabReselected(tab: TabLayout.Tab?) { }
        })
    }

    fun setUpButtonText() {
        when (binding.WebServiceTabLayout.selectedTabPosition) {
            0 -> {
                binding.webServiceType = WebServiceType.Retrofit
            }
            1-> {
                binding.webServiceType = WebServiceType.HttpURLConnection
            }
            2 -> {
                binding.webServiceType = WebServiceType.OkHttp3
            }
        }
    }
}