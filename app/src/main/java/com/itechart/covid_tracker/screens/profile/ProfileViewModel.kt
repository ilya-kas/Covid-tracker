package com.itechart.covid_tracker.screens.profile

import androidx.lifecycle.ViewModel
import com.itechart.covid_tracker.model.Model

class ProfileViewModel: ViewModel() {
    var user = Model.loadUser()
}