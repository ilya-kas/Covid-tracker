package com.itechart.covid_tracker.model.database.favorites

import com.itechart.covid_tracker.model.entities.Country

interface FavoritesProvider {
    fun loadFavorites(countries: List<Country>)
    fun starred(country: Country)
}