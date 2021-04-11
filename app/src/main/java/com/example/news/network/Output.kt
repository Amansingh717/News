package com.example.news.network

sealed class Output<ResponseType>(val value: Any?) {
    data class Success<ResponseType>(val data: ResponseType) : Output<ResponseType>(data)
    data class Failure<ResponseType>(val exception: String) : Output<ResponseType>(exception)
}