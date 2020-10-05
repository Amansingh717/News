package com.example.news.network

import androidx.annotation.Nullable

class NetworkResponse<ResponseType>(var status: Status, val data: ResponseType?) {
    sealed class Status {
        object SUCCESS : Status()
        object FAILURE : Status()
        object LOADING : Status()
    }

    private var call: ApiInteractor<ResponseType>? = null

    companion object {
        @JvmStatic
        fun <ResponseType> success(data: ResponseType): NetworkResponse<ResponseType> {
            return NetworkResponse(Status.SUCCESS, data)
        }

        @JvmStatic
        fun <ResponseType> error(@Nullable data: ResponseType? = null): NetworkResponse<ResponseType> {
            return NetworkResponse(Status.FAILURE, data)
        }

        @JvmStatic
        fun <ResponseType> loading(@Nullable data: ResponseType? = null): NetworkResponse<ResponseType> {
            return NetworkResponse(Status.LOADING, data)
        }
    }

    fun saveCall(call: ApiInteractor<ResponseType>?): NetworkResponse<ResponseType> {
        this.call = call
        return this
    }
}