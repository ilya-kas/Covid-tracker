package com.itechart.covid_tracker.screens.profile

import androidx.lifecycle.ViewModel
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.model.Model

class ProfileViewModel: ViewModel() {
    val model = App.appComponent.getModel()//todo
    var user = model.loadUser()
}