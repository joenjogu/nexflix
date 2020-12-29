package com.joenjogu.nexflix.data

import okhttp3.Interceptor
import okhttp3.Response
    
class TokenInterceptor : Interceptor {
    private val apiKey = "2d9aa26f9b71ca6d8a3db85d730e19a4"
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val url = original.url.newBuilder().addQueryParameter("api_key", apiKey).build()
        original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}