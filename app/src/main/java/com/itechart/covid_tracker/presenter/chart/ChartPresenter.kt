package com.itechart.covid_tracker.presenter.chart

import com.itechart.covid_tracker.model.Model

class ChartPresenter {
    var number = 0 //which group we are looking at
        set(value) {
            field = value
            days = Model.loadDays(value)
        }
    lateinit var days: ArrayList<Day>
    val listLength
        get() = days.size
}