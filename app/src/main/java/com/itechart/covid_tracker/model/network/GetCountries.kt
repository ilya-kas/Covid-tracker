package com.itechart.covid_tracker.model.network

data class GetCountries(
    val get: String,
    val parameters: Array<String>,
    val errors: Array<String>,
    val results: Int,
    val response: Array<String>
)