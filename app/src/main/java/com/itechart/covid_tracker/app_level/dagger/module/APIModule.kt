package com.itechart.covid_tracker.app_level.dagger.module

import com.itechart.covid_tracker.model.network.CovidAPI
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class APIModule{
    val BASE_URL = "https://covid-193.p.rapidapi.com"

    @Provides
    fun getConvertor(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(getConvertor())
        .build()

    @Singleton
    @Provides
    fun getCovidAPI(): CovidAPI {
        return getRetrofit().create(CovidAPI::class.java)
    }
}