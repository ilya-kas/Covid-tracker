package com.itechart.covid_tracker.screens.chart

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.model.entities.Day
import com.itechart.covid_tracker.model.network.CovidStatsProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChartViewModel @Inject constructor(val covidStatsProvider: CovidStatsProvider): ViewModel() {

    var number = 0 //which country we are looking at
    var days = MutableLiveData<List<Day>>()
    val listLength
        get() = days.value!!.size

    fun loadDays(){
        val stat = covidStatsProvider.loadDays(number)

        val mainHandler = Handler(App.appComponent.getContext().mainLooper)
        mainHandler.post {
            days.value = stat
        }
    }
}