package com.example.news.utility

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.news.NewsApplication

object AppUtils {
    fun isNetworkConnected(): Boolean {
        val connectivityManager = NewsApplication.getAppContext()
            .getSystemService(Context.CONNECTIVITY_SERVICE)
                as? ConnectivityManager
        val network = connectivityManager?.activeNetwork
            ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            ?: return false

        return (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
    }
}