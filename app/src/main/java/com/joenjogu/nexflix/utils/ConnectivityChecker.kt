package com.joenjogu.nexflix.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object ConnectivityChecker {

    fun isConnected(context: Context): Boolean {
        var isConnected = false

        val connManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connManager.activeNetwork ?: return false
            val activeNet =
                connManager.getNetworkCapabilities(networkCapabilities) ?: return false

            isConnected = when {
                activeNet.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNet.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNet.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connManager.run {
                connManager.activeNetworkInfo?.run {
                    isConnected = when(type) {
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }
        return isConnected
    }
}