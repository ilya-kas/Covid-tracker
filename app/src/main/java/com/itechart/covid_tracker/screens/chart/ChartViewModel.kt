package com.itechart.covid_tracker.screens.chart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itechart.covid_tracker.model.FilledCountriesProvider
import com.itechart.covid_tracker.model.entities.Day
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChartViewModel @Inject constructor(private val filledCountriesProvider: FilledCountriesProvider): ViewModel() {

    var number = 0 //which country we are looking at
    var days = MutableLiveData<List<Day>>().apply { value = filledCountriesProvider.loadDays(number) }
    val listLength
        get() = days.value!!.size

    fun updateDays(){
        days.value = filledCountriesProvider.loadDays(number)
    }
}