package com.itechart.covid_tracker.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://covid-193.p.rapidapi.com"

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api:CovidAPI by lazy {
        retrofit.create(CovidAPI::class.java)
    }
}