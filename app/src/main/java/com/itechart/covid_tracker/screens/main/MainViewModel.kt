package com.itechart.covid_tracker.screens.main

import androidx.lifecycle.ViewModel
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.model.Model
import com.itechart.covid_tracker.model.entities.Country
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel: ViewModel() {
    val model = App.appComponent.getModel()//todo

    var countries = model.countries
        private set
    val listLength
        get() = countries.size
    private var lastFilter = ""

    fun starred(nom: Int){
        countries[nom].favorite = !countries[nom].favorite
    }

    /**
     * filters showing items by names
     */
    fun filter(filter: String){
        if (filter.length < lastFilter.length)
            countries = model.countries

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