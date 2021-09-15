package com.itechart.covid_tracker.model.database.favorites

import com.itechart.covid_tracker.model.entities.Country

interface FavoritesProvider {
    /**
     * this method must be called from coroutine
     */
    fun getFavorites(): List<Country>

    fun starred(country: Country)
}