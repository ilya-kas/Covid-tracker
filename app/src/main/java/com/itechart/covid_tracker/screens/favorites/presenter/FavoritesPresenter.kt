package com.itechart.covid_tracker.screens.favorites.presenter

import com.itechart.covid_tracker.model.Model
import com.itechart.covid_tracker.screens.favorites.view.FavoritesFragment
import java.util.stream.Collectors

class FavoritesPresenter(private val fragment: FavoritesFragment) {
    val countries = Model.countries.parallelStream().filter{ x -> x.favorite }.collect(Collectors.toList())
    val listLength
        get() = countries.size

    fun getColor(id: Int): Int{
        return fragment.resources.getColor(id, fragment.resources.newTheme())
    }
}