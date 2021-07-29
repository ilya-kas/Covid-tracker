package com.itechart.covid_tracker.model.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class LoggingInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        Log.i("http interceptor logs","Intercepted headers: ${request.headers} from URL: ${request.url}")

        return chain.proceed(request)
    }
}