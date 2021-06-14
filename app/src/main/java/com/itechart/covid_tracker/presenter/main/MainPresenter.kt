package com.itechart.covid_tracker.presenter.main

import com.itechart.covid_tracker.model.Model

class MainPresenter {
    val countries = Model.loadGroups()
    val listLength
        get() = countries.size

    fun starred(nom: Int){
        countries[nom].favorite = !countries[nom].favorite
    }
}