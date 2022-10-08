package com.itechart.covid_tracker.model

import com.itechart.covid_tracker.model.database.favorites.FavoritesProvider
import com.itechart.covid_tracker.logic.entity.Country
import com.itechart.covid_tracker.logic.entity.Day
import com.itechart.covid_tracker.model.network.CovidApiRepository
import com.itechart.covid_tracker.model.network.CovidStatsProvider
import java.util.stream.Collectors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilledCountriesProvider @Inject constructor(private val covidStatsProvider: CovidApiRepository, private val favoritesProvider: FavoritesProvider): CovidStatsProvider, FavoritesProvider {
    lateinit var countries: List<Country>

    suspend fun preload(){
        countries = covidStatsProvider.loadCountries()
        val favorite = favoritesProvider.getFavorites()
        for (country in favorite)
            countries[country.id].favorite = true
    }

    /**
     * no need in this function because countries field is public
     */
    override suspend fun loadCountries(): List<Country> = countries

    override suspend fun preloadDays(nom: Int) {
        covidStatsProvider.preloadDays(nom)
    }

    override suspend fun loadDays(nom: Int): List<Day>{
        if (countries[nom].daysInfo == null)
            covidStatsProvider.preloadDays(nom)
        return countries[nom].daysInfo!!
    }

    override fun getFavorites(): List<Country> = countries.parallelStream().filter{ x -> x.favorite }
                                                                                    .collect(Collectors.toList())

    override fun starred(country: Country) {
        country.favorite = !country.favorite
        countries[country.id].favorite = country.favorite
    }
}