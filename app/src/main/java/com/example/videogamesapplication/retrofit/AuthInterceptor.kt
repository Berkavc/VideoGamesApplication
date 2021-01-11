package com.example.videogamesapplication.retrofit

import com.example.videogamesapplication.Constants
import okhttp3.Interceptor
import okhttp3.Response

internal class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .header("Content-Type", "application/x-www-form-urlencoded")
            .header("x-rapidapi-key", Constants.RAPID_API_KEY)
            .header("x-rapidapi-host", Constants.RAPID_API_HOST)
            .build()
        return chain.proceed(request)
    }
}