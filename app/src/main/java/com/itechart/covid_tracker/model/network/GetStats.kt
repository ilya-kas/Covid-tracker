package com.itechart.covid_tracker.model.network

data class GetStats(
    val `get`: String,
    val parameters: Any,
    val errors: List<Any>,
    val results: Int,
    val response: List<Response>
) {

    data class Response(
        val continent: String,
        val country: String,
        val population: Int,
        val cases: Cases,
        val deaths: Deaths,
        val tests: Tests,
        val day: String,
        val time: String
    ) {
        data class Cases(
            val new: String,
            val active: Int,
            val critical: Int,
            val recovered: Int,
            val `1M_pop`: String,
            val total: Int
        )

        data class Deaths(
            val new: String,
            val `1M_pop`: String,
            val total: Int
        )

        data class Tests(
            val `1M_pop`: String,
            val total: Int
        )
    }
}