package com.itechart.covid_tracker.screens.chart.presenter

import com.itechart.covid_tracker.model.entities.Day
import com.itechart.covid_tracker.model.Model

class ChartPresenter {
    var number = 0 //which group we are looking at
        set(value) {
            field = value
            days = Model.loadDays(value)
        }
    var days = ArrayList<Day>()
    val listLength
        get() = days.size
}