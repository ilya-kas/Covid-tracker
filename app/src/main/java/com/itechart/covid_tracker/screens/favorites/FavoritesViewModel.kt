package com.itechart.covid_tracker.screens.favorites

import androidx.lifecycle.ViewModel
import com.itechart.covid_tracker.model.FilledCountriesProvider
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(private val filledCountriesProvider: FilledCountriesProvider): ViewModel() {
    val countries
        get() = filledCountriesProvider.getFavorites()
    val listLength
        get() = countries.size


    fun itemRemoved(nom: Int){
        filledCountriesProvider.starred(countries[nom])
    }
}