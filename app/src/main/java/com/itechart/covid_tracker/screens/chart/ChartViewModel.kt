package com.itechart.covid_tracker.screens.chart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.model.Model
import com.itechart.covid_tracker.model.entities.Day
import javax.inject.Inject

class ChartViewModel @Inject constructor(val model: Model): ViewModel() {

    var number = 0 //which group we are looking at
        set(value) {
            field = value
            days.value = model.getDays(value)
        }
    var days = MutableLiveData<List<Day>>()
    val listLength
        get() = days.value!!.size
}