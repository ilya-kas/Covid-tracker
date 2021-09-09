package com.itechart.covid_tracker.model.network

import com.itechart.covid_tracker.model.entities.Country
import com.itechart.covid_tracker.model.entities.Day

interface CovidStatsProvider {
    suspend fun loadCountries(): List<Country>
    suspend fun loadDays(country: Country):List<Day>
}