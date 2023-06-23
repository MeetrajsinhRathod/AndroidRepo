package com.example

import android.app.Application

class AppClass: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: AppClass
            private set
    }
}