package com.example.news.network

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "RetrofitNetworkCallback"

abstract class NetworkCallback<T> : Callback<T> {
    override fun onFailure(call: Call<T>?, t: Throwable) {
        Log.d(TAG, t.message.toString())
        onFailure(t.message.toString())
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        onSuccess(response.body()!!)
    }

    abstract fun onFailure(msg: String)
    abstract fun onSuccess(response: T)
}