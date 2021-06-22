package com.itechart.covid_tracker.model.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface CovidAPI {

    @Headers("x-rapidapi-key: 2baa79f4d3mshd15a62ab3425f06p1312eajsn7b856793ea3b",
            "x-rapidapi-host: covid-193.p.rapidapi.com")
    @GET("/countries")
    fun getCountiesList(): Call<GetCountries>
}