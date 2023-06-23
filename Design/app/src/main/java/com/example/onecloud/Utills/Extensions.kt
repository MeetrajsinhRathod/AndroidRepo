package com.example.onecloud.Utills

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone


fun String.formatDate(pattern: String): String {
    val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
    isoFormat.timeZone = TimeZone.getTimeZone("UTC")
    val date = isoFormat.parse(this)
    val currentFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return currentFormat.format(date)
}