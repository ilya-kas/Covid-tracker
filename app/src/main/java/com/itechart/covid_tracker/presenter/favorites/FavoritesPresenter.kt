package com.itechart.covid_tracker.presenter.favorites

import com.itechart.covid_tracker.model.Model
import com.itechart.covid_tracker.view.favorites_screen.FavoritesFragment
import java.util.stream.Collectors

class FavoritesPresenter(private val fragment: FavoritesFragment) {
    val countries = Model.loadCountries().parallelStream().filter{ x -> x.favorite }.collect(Collectors.toList())
    val listLength
        get() = countries.size

    fun getColor(id: Int): Int{
        return fragment.resources.getColor(id, fragment.resources.newTheme())
    }
}