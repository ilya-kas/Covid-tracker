package com.itechart.covid_tracker.model.network

import com.itechart.covid_tracker.model.entities.Country
import com.itechart.covid_tracker.model.entities.Day

interface CovidStatsProvider {
    val countries: List<Country>
    suspend fun loadCountries(): List<Country>
    suspend fun preloadDays(nom: Int)
    fun loadDays(nom: Int):List<Day>
}