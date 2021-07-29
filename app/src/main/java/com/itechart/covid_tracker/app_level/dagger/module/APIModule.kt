package com.itechart.covid_tracker.app_level.dagger.module

import com.itechart.covid_tracker.model.network.CovidAPI
import com.itechart.covid_tracker.model.network.LoggingInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://covid-193.p.rapidapi.com"

@Module
class APIModule{

    @Provides
    fun getConvertor(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    fun getLoggingInterceptor(): Interceptor = LoggingInterceptor()

    @Provides
    fun getOkHTTPClient(): OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(getLoggingInterceptor())
        .build()

    @Provides
    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(getOkHTTPClient())
        .addConverterFactory(getConvertor())
        .build()

    @Singleton
    @Provides
    fun getCovidAPI(): CovidAPI {
        return getRetrofit().create(CovidAPI::class.java)
    }
}