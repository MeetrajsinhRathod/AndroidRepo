package com.example.webService.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.webService.api.HttpURLConnectionBase
import com.example.webService.api.ResponseType
import com.example.webService.api.HttpRequestEnum
import com.example.webService.api.OkHttp3Base
import com.example.webService.instance.RetrofitInstance
import com.example.webService.model.GetUserDataResponse
import com.example.webService.model.LoginRequest
import com.example.webService.model.UpdateUserDataRequest
import com.example.webService.model.UpdateUserDataResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class UpdateRequestViewModel: ViewModel() {

    private var updateResponse = MutableLiveData<UpdateUserDataResponse?>()

    private fun convertToJsonString(request: UpdateUserDataRequest): String{
        val jsonObject = JSONObject()
        jsonObject.put("name", request.name)
        jsonObject.put("job", request.job)
        return jsonObject.toString()
    }

    private fun getResponse(jsonResponse: String): UpdateUserDataResponse {
        val jsonObject = JSONTokener(jsonResponse).nextValue() as JSONObject
        val name = jsonObject.getString("name")
        val job = jsonObject.getString("job")
        val updatedAt = jsonObject.getString("updatedAt")
        return UpdateUserDataResponse(name, job, updatedAt)
    }

    fun getUpdateStatus(): MutableLiveData<UpdateUserDataResponse?> {
        return updateResponse
    }

    fun updateUserDataWithRetrofit(updateUserDataRequest: UpdateUserDataRequest, userId: Int, requestType: HttpRequestEnum) {
        GlobalScope.launch(Dispatchers.IO) {
            val response =
                if (requestType == HttpRequestEnum.PUT()) {
                    RetrofitInstance.reqresApi.updateUserDataWithPut(updateUserDataRequest, userId)
                } else {
                    RetrofitInstance.reqresApi.updateUserDataWithPatch(updateUserDataRequest, userId)
                }
            if (response.code() == 200) {
                updateResponse.postValue(response.body())
            }
        }
    }

    fun updateUserDataWithHttpUrlConnection(updateUserDataRequest: UpdateUserDataRequest, requestType: HttpRequestEnum) {
        val jsonString = convertToJsonString(updateUserDataRequest)
        val base = HttpURLConnectionBase(requestType)
        base.callApi(jsonString) { responseType, response ->
            if (responseType == ResponseType.Success) {
                updateResponse.postValue(getResponse(response))
            }
        }
    }

    fun updateUserDataWithOkHttp3(updateUserDataRequest: UpdateUserDataRequest, requestType: HttpRequestEnum) {
        val base = OkHttp3Base(requestType)
        val jsonString = convertToJsonString(updateUserDataRequest)
        base.client.newCall(base.callApi(jsonString)).enqueue(object: okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.e("OkHTTP3_ERROR", e.toString())
            }
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if ( response.code() == 200 ) {
                    val userData = Gson().fromJson(response.body()?.string(), UpdateUserDataResponse::class.java)
                    updateResponse.postValue(userData)
                } else { updateResponse.postValue(null) }
            }
        })
    }
}