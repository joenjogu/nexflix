package com.joenjogu.nexflix.data.core

import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> getNetworkResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val networkResponse = call()
            if (networkResponse.isSuccessful) {
                val body = networkResponse.body()
                if (body != null) return Result.success(body)
            }
            return Result.error(networkResponse.message())
        } catch (e: Exception) {
            return Result.error(e.message)
        }
    }
}