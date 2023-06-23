package com.example.webService.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.webService.api.HttpURLConnectionBase
import com.example.webService.api.ResponseType
import com.example.webService.api.HttpRequestEnum
import com.example.webService.api.OkHttp3Base
import com.example.webService.instance.RetrofitInstance
import com.example.webService.model.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class DeleteRequestViewModel: ViewModel() {

    private var requestMethod = HttpRequestEnum.DELETE()
    private var deleteResponse = MutableLiveData<String>()

    private fun setDeleteStatus(responseType: ResponseType) {
        MainScope().launch(Dispatchers.IO) {
            if (responseType == ResponseType.Success) {
                deleteResponse.postValue("Delete Status: Data deleted Successfully")
            } else {
                deleteResponse.postValue("Delete Status: Error Occurred")
            }
        }
    }

    fun getDeleteResponse(): MutableLiveData<String> {
        return deleteResponse
    }

    fun deleteUserWithRetrofit(userId: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            val response = RetrofitInstance.reqresApi.deleteUser(userId)
            if (response.code() == 204) {
                setDeleteStatus(ResponseType.Success)
            } else {
                setDeleteStatus(ResponseType.Failure)
            }
        }
    }

    fun deleteUserWithHttpUrlConnection(userId: Int) {
        requestMethod.setId(userId)
        val base = HttpURLConnectionBase(requestMethod)
        base.callApi(null) { responseType, _ ->
            setDeleteStatus(responseType)
        }
    }

    fun deleteUserWithOkHttp3(userId: Int) {
        requestMethod.setId(userId)
        val base = OkHttp3Base(requestMethod)
        base.client.newCall(base.callApi(null)).enqueue(object: okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.e("OkHTTP3_ERROR", e.toString())
                setDeleteStatus(ResponseType.Failure)
            }
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if (response.code() == 204) {
                    setDeleteStatus(ResponseType.Success)
                } else {
                    setDeleteStatus(ResponseType.Failure)
                }
            }
        })
    }
}