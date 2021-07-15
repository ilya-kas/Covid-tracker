package com.itechart.covid_tracker.screens.chart

import androidx.lifecycle.ViewModel
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.model.Model
import com.itechart.covid_tracker.model.entities.Day

class ChartViewModel: ViewModel() {
    val model = App.appComponent.getModel()//todo

    var number = 0 //which group we are looking at
        set(value) {
            field = value
            days = model.getDays(value)
        }
    var days:List<Day> = ArrayList()
        private set
    val listLength
        get() = days.size
}