package com.itechart.covid_tracker.presenter.chart

import com.itechart.covid_tracker.model.Model

object ChartPresenter {
    var number = 0 //which group we are looking at
        set(value) {
            field = value
            days = Model.loadItems(value)
        }
    lateinit var days: ArrayList<Day>//todo rename
    val listLength
        get() = days.size
}