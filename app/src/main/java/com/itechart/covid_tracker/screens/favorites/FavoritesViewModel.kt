package com.itechart.covid_tracker.screens.favorites

import androidx.lifecycle.ViewModel
import com.itechart.covid_tracker.model.Model
import java.util.stream.Collectors

class FavoritesViewModel: ViewModel() {
    val countries = Model.countries.parallelStream().filter{ x -> x.favorite }.collect(Collectors.toList())
    val listLength
        get() = countries.size
}