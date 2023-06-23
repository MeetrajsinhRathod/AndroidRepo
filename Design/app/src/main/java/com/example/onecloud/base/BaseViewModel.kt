package com.example.onecloud.base

import androidx.lifecycle.ViewModel
import com.example.onecloud.api.RetrofitObject

abstract class BaseViewModel: ViewModel() {

    protected val apiService by lazy {
        RetrofitObject.apiService
    }
}