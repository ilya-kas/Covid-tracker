package com.itechart.covid_tracker.screens.main.presenter

import com.itechart.covid_tracker.model.Model
import com.itechart.covid_tracker.model.entities.Country
import java.util.*
import kotlin.collections.ArrayList

class MainPresenter {
    var countries = Model.countries
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
            countries = Model.countries

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