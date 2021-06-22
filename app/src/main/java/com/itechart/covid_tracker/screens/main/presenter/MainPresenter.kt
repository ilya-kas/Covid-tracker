package com.itechart.covid_tracker.screens.main.presenter

import com.itechart.covid_tracker.model.Model

class MainPresenter {
    val countries = Model.countries
    val listLength
        get() = countries.size

    fun starred(nom: Int){
        countries[nom].favorite = !countries[nom].favorite
    }
}