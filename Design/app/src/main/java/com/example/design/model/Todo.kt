package com.example.design.model

import android.os.Parcel
import android.os.Parcelable

data class Todo(var task: String, var isCompleted: Boolean = false)