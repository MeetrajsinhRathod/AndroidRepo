package com.example.webService.api

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

enum class ResponseType {
    Success,
    Failure
}

class HttpURLConnectionBase(
    private val requestType: HttpRequestEnum
) {

    fun callApi(jsonString: String? ,completionHandler:(responseType: ResponseType, response: String) -> Unit) {
          GlobalScope.launch(Dispatchers.IO) {
            val url = URL(requestType.getTargetUrl())
            val httpURLConnection = url.openConnection() as HttpURLConnection
            requestType.setUpURLConnection(httpURLConnection)
              if (!jsonString.isNullOrEmpty()) { requestType.setOutputStream(jsonString,httpURLConnection) }
            try {
                val responseCode = httpURLConnection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val response =
                        httpURLConnection.inputStream.bufferedReader().use { it.readText() }
                    completionHandler(ResponseType.Success, response)
                } else if (requestType is HttpRequestEnum.DELETE && responseCode == 204) {
                    completionHandler(ResponseType.Success, "No content")
                } else {
                    Log.e("HTTPURLCONNECTION_ERROR", responseCode.toString() + httpURLConnection.responseMessage)
                    val error = httpURLConnection.errorStream.bufferedReader().use { it.readText() }
                    completionHandler(ResponseType.Failure, error)
                }
            } finally {
                httpURLConnection.disconnect()
            }
        }
    }
}