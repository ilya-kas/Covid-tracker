package com.itechart.covid_tracker.screens.main

import androidx.lifecycle.ViewModel
import com.itechart.covid_tracker.model.database.favorites.FavoritesProvider
import com.itechart.covid_tracker.model.entities.Country
import com.itechart.covid_tracker.model.network.CovidStatsProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class MainViewModel @Inject constructor(val covidStatsProvider: CovidStatsProvider, val favoritesProvider: FavoritesProvider): ViewModel() {
    var countries:List<Country> = ArrayList()
    private var lastFilter = ""

    fun starred(nom: Int){
        countries[nom].favorite = !countries[nom].favorite
        favoritesProvider.starred(countries[nom])
    }

    fun loadCountries(){
        GlobalScope.launch {
            countries = covidStatsProvider.loadCountries()
        }
    }

    /**
     * filters showing items by names
     */
    fun filter(filter: String){
        if (filter.length < lastFilter.length)
            countries = covidStatsProvider.countries

        val tmp = ArrayList<Country>()
        for (x in countries) {
            val name = x.name.toLowerCase(Locale.ROOT)
            if (filter == "" || name.contains(filter.toLowerCase(Locale.ROOT)))
                tmp += x
        }
        countries = tmp

        lastFilter = filter
    }
}