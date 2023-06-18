package com.example.webService.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.webService.api.HttpURLConnectionBase
import com.example.webService.api.ResponseType
import com.example.webService.api.HttpRequestEnum
import com.example.webService.api.OkHttp3Base
import com.example.webService.instance.RetrofitInstance
import com.example.webService.model.GetUserDataResponse
import com.example.webService.model.UserData
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

class GetUserDataViewModel: ViewModel() {

    private var requestMethod = HttpRequestEnum.GET
    private var liveDataList = MutableLiveData<List<UserData>?>()

    private fun getResponse(jsonResponse: String): List<UserData> {
        val jsonObject = JSONTokener(jsonResponse).nextValue() as JSONObject
        val jsonArray = jsonObject.getJSONArray("data")
        val userDataList = mutableListOf<UserData>()
        for (i in 0 until jsonArray.length()) {
            val id = jsonArray.getJSONObject(i).getInt("id")
            val email = jsonArray.getJSONObject(i).getString("email")
            val firstname = jsonArray.getJSONObject(i).getString("first_name")
            val lastname = jsonArray.getJSONObject(i).getString("last_name")
            val avatar = jsonArray.getJSONObject(i).getString("avatar")
            val userData = UserData(id, email, firstname, lastname, avatar)
            userDataList.add(userData)
        }
        return userDataList
    }

    fun getUserDataList(): MutableLiveData<List<UserData>?> {
        return liveDataList
    }

    fun callGetUserApiUsingRetrofit() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = RetrofitInstance.reqresApi.getUserData()
            if (response.isSuccessful) {
                liveDataList.postValue(response.body()?.data)
            } else { liveDataList.postValue(null) }
        }
    }

    fun callGetUserApiUsingHttpURLConnection() {
        val base = HttpURLConnectionBase(requestMethod)
        base.callApi(null) { responseType, response ->
            if (responseType == ResponseType.Success) {
                liveDataList.postValue(getResponse(response))
            } else {
                liveDataList.postValue(null)
            }
        }
    }

    fun callGetUserApiUsingOkHttp3() {
        val base = OkHttp3Base(requestMethod)
        base.client.newCall(base.callApi(null)).enqueue(object: okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                liveDataList.postValue(null)
            }
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val userData = (Gson().fromJson(response.body()?.string(), GetUserDataResponse::class.java)).data
                liveDataList.postValue(userData)
            }
        })
    }
}