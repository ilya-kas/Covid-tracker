package com.itechart.covid_tracker.screens.favorites

import androidx.lifecycle.ViewModel
import com.itechart.covid_tracker.model.database.favorites.FavoritesProvider
import com.itechart.covid_tracker.model.entities.Country
import com.itechart.covid_tracker.model.network.CovidStatsProvider
import java.util.stream.Collectors
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(val covidStatsProvider: CovidStatsProvider, val favoritesProvider: FavoritesProvider): ViewModel() {
    val countries: List<Country>
        get(){
            favoritesProvider.loadFavorites(covidStatsProvider.countries) //updating favorites stats
            return covidStatsProvider.countries.parallelStream().filter{ x -> x.favorite }.collect(Collectors.toList())
        }
    val listLength
        get() = countries.size

    fun itemRemoved(nom: Int){
        countries[nom].favorite = false
        favoritesProvider.starred(countries[nom])
    }
}