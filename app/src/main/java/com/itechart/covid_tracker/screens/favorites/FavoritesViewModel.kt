package com.itechart.covid_tracker.screens.favorites

import androidx.lifecycle.ViewModel
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.model.Model
import java.util.stream.Collectors
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(val model: Model): ViewModel() {
    val countries
        get() = model.countries.parallelStream().filter{ x -> x.favorite }.collect(Collectors.toList())
    val listLength
        get() = countries.size

    fun removed(nom: Int){
        countries[nom].favorite = false
    }
}