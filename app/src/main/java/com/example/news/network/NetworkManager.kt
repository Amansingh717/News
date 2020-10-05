package com.example.news.network

import com.example.news.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkManager {
    /**
     * Creating Retrofit
     */
    fun getWebService(): WebService = getRetrofit(getOkHttpClient())
        .create(WebService::class.java)

    /**
     * Getting Retrofit
     */
    private fun getRetrofit(client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    /**
     * Intercepting to get logs on debug builds
     */
    private fun getOkHttpClient() = OkHttpClient()
        .newBuilder().apply {
            readTimeout(60, TimeUnit.SECONDS)
            connectTimeout(60, TimeUnit.SECONDS)

            // to enable logging in debug mode
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(interceptor)
            }
        }.build()
}