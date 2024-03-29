package com.itechart.covid_tracker.screens.main

import androidx.lifecycle.ViewModel
import com.itechart.covid_tracker.model.FilledCountriesProvider
import com.itechart.covid_tracker.logic.entity.Country
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class MainViewModel @Inject constructor(private val filledCountriesProvider: FilledCountriesProvider): ViewModel() {
    var countries = filledCountriesProvider.countries
    private var lastFilter = ""

    fun starred(nom: Int){
        filledCountriesProvider.starred(countries[nom])
    }

    /**
     * filters showing items by names
     */
    fun filter(filter: String){
        if (filter.length < lastFilter.length)
            countries = filledCountriesProvider.countries

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