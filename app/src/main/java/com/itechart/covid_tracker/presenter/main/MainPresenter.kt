package com.itechart.covid_tracker.presenter.main

import androidx.fragment.app.FragmentManager
import com.itechart.covid_tracker.model.Model

object MainPresenter {
    val countries = Model.loadGroups()
    val listLength
        get() = countries.size
    lateinit var fragmentManager: FragmentManager

    fun starred(nom: Int){
        countries[nom].favorite = !countries[nom].favorite
    }
}