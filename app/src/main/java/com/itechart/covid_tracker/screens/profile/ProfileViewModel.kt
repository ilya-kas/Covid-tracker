package com.itechart.covid_tracker.screens.profile

import androidx.lifecycle.ViewModel
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.model.Model
import javax.inject.Inject

class ProfileViewModel @Inject constructor(val model: Model): ViewModel() {
    var user = model.loadUser()
}