package com.example.news.extensions

import android.content.Context
import android.widget.Toast
import com.example.news.BuildConfig

fun Context?.makeDebugToast(message: String? = "", length: Int = Toast.LENGTH_LONG) {
    if (BuildConfig.DEBUG && this != null) {
        Toast.makeText(this, message, length).show()
    }
}