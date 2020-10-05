package com.example.news.network

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.utility.AppUtils
import retrofit2.Call

abstract class ApiInteractor<ResponseType> {
    private val result = MutableLiveData<NetworkResponse<ResponseType>>()

    val getAsLiveData: LiveData<NetworkResponse<ResponseType>>
        get() = result

    init {
        fetchApi()
    }

    private fun fetchApi() {
        result.value = NetworkResponse.loading()
        if (AppUtils.isNetworkConnected()) {
            createCall().enqueue(object : NetworkCallback<ResponseType>() {
                override fun onFailure(msg: String) {
                    result.value = NetworkResponse.error<ResponseType>().saveCall(this@ApiInteractor)
                }

                override fun onSuccess(response: ResponseType) {
                    result.value = NetworkResponse.success(response).saveCall(this@ApiInteractor)
                }
            })
        } else {
            result.value = NetworkResponse.error()
        }
    }

    @MainThread
    protected abstract fun createCall(): Call<ResponseType>
}