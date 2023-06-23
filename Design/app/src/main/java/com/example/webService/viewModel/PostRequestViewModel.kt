package com.example.webService.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.webService.api.HttpRequestEnum
import com.example.webService.api.HttpURLConnectionBase
import com.example.webService.api.OkHttp3Base
import com.example.webService.api.ResponseType
import com.example.webService.instance.RetrofitInstance
import com.example.webService.model.LoginRequest
import com.example.webService.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class PostRequestViewModel: ViewModel() {

    private var requestMethod = HttpRequestEnum.POST
    var loginStatus = MutableLiveData("Not Logged In")

    private fun setLoginStatus(responseType: ResponseType) {
        if (responseType == ResponseType.Success) {
            loginStatus.postValue( "Logged In")
        } else {
            loginStatus.postValue("Not Logged In")
        }
    }

    private fun convertToJsonString(request: LoginRequest): String{
        val jsonObject = JSONObject()
        jsonObject.put("email", request.email)
        jsonObject.put("password", request.password)
        return jsonObject.toString()
    }

    fun signInWithRetrofit(loginRequest: LoginRequest) {
        GlobalScope.launch(Dispatchers.IO) {
            val response = RetrofitInstance.reqresApi.signInUser(loginRequest)
            if (response.isSuccessful) {
                setLoginStatus(ResponseType.Success)
            } else { setLoginStatus(ResponseType.Failure) }
        }
    }

    fun signInWithHttpUrlConnection(loginRequest: LoginRequest) {
        val jsonString = convertToJsonString(loginRequest)
        val base = HttpURLConnectionBase(requestMethod)
        base.callApi(jsonString) { responseType, _ ->
            setLoginStatus(responseType)
        }
    }

    fun signInWithOkHttp3(loginRequest: LoginRequest) {
        val base = OkHttp3Base(requestMethod)
        val jsonString = convertToJsonString(loginRequest)
        base.client.newCall(base.callApi(jsonString)).enqueue(object: okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.e("OkHTTP3_ERROR", e.toString())
                setLoginStatus(ResponseType.Failure)
            }
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if ( response.code() == 200 ) {
                    setLoginStatus(ResponseType.Success)
                } else { setLoginStatus(ResponseType.Failure) }
            }
        })
    }
}