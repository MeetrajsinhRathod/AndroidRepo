package com.example.webService.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.lifecycle.ViewModelProvider
import com.example.design.databinding.ActivityUpdateRequestBinding
import com.example.design.helper.hideKeyboardOnTouchOutside
import com.example.webService.api.HttpRequestEnum
import com.example.webService.api.WebServiceType
import com.example.webService.model.UpdateUserDataRequest
import com.example.webService.viewModel.UpdateRequestViewModel

class UpdateRequestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateRequestBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[UpdateRequestViewModel::class.java]
    }
    private lateinit var requestType: HttpRequestEnum
    private lateinit var webServiceType: WebServiceType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setResponseObserver()
        requestType = (intent.getSerializableExtra("RequestType") as? HttpRequestEnum) ?: HttpRequestEnum.PUT()
        binding.btnSubmitData.setOnClickListener {
            if (binding.etId.text.isNotEmpty() && binding.etJob.text.isNotEmpty() && binding.etUsername.text.isNotEmpty()) {
                callUpdateUserDataApi()
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

    private fun setResponseObserver() {
        viewModel.getUpdateStatus().observe(this) {
            binding.tvResponseName.text = it?.name ?: "NA"
            binding.tvResponseJob.text = it?.job ?: "NA"
            binding.tvResponseUpdatedAt.text = it?.updatedAt ?: "NA"
        }
    }

    private fun callUpdateUserDataApi() {
        val userId = binding.etId.text.toString().toInt()
        val updateUserRequest = UpdateUserDataRequest(binding.etUsername.text.toString(), binding.etJob.text.toString())

        if (requestType is HttpRequestEnum.PUT) {
            (requestType as HttpRequestEnum.PUT).setId(binding.etId.text.toString().toInt())
        } else {
            (requestType as HttpRequestEnum.PATCH).setId(binding.etId.text.toString().toInt())
        }

        when (intent.getSerializableExtra("WebServiceType") as? WebServiceType) {
            WebServiceType.Retrofit -> viewModel.updateUserDataWithRetrofit(updateUserRequest, userId, requestType)
            WebServiceType.HttpURLConnection -> viewModel.updateUserDataWithHttpUrlConnection(updateUserRequest, requestType)
            else -> { viewModel.updateUserDataWithOkHttp3(updateUserRequest, requestType) }
        }
    }
}