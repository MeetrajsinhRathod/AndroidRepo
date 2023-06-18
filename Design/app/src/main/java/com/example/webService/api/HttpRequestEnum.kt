package com.example.webService.api

import java.io.OutputStreamWriter
import java.io.Serializable
import java.net.HttpURLConnection

enum class  WebServiceType {
    Retrofit,
    HttpURLConnection,
    OkHttp3
}
sealed class HttpRequestEnum: Serializable {

    private val baseUrl = "https://reqres.in/api/"

    fun getRequestMethod(): String {
        return when (this) {
            is GET -> "GET"
            is POST -> "POST"
            is PUT -> "PUT"
            is PATCH -> "PATCH"
            is DELETE -> "DELETE"
        }
    }

    fun getTargetUrl(): String {
        return when (this) {
            is GET -> baseUrl+"users"
            is POST -> baseUrl+"login"
            is PUT -> baseUrl+"users/${this.getId()}"
            is PATCH -> baseUrl+"users/${this.getId()}"
            is DELETE -> baseUrl+"users/${this.getId()}"
        }
    }

    fun setUpURLConnection(connection: HttpURLConnection) {
        connection.requestMethod = this.getRequestMethod()
        connection.setRequestProperty("Content-Type", "application/json")
        connection.setRequestProperty("Accept", "application/json")
        connection.doInput = true
    }

    fun setOutputStream(jsonString: String, connection: HttpURLConnection) {
        connection.doOutput = true
        val outputStreamWriter = OutputStreamWriter(connection.outputStream)
        outputStreamWriter.write(jsonString)
        outputStreamWriter.flush()
    }

    object GET : HttpRequestEnum()

    object POST : HttpRequestEnum()

    class PUT(): HttpRequestEnum() {
        private var id: Int = 0
        fun getId() = id
        fun setId(id: Int) {
            this.id = id
        }
    }

    class PATCH(): HttpRequestEnum() {
        private var id: Int = 0
        fun getId() = id
        fun setId(id: Int) {
            this.id = id
        }
    }

    class DELETE(): HttpRequestEnum() {
        private var id: Int = 0
        fun getId() = id
        fun setId(id: Int) {
            this.id = id
        }
    }
}