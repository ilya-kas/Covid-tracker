package com.itechart.covid_tracker.model.network

import com.itechart.covid_tracker.logic.entity.Country
import com.itechart.covid_tracker.logic.entity.Day

interface CovidStatsProvider {
    suspend fun loadCountries(): List<Country>
    suspend fun preloadDays(nom: Int)
    suspend fun loadDays(nom: Int):List<Day>
}