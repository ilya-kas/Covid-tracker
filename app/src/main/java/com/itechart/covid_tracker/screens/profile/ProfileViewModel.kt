package com.itechart.covid_tracker.screens.profile

import androidx.lifecycle.ViewModel
import com.itechart.covid_tracker.model.database.settings.SettingsProvider
import com.itechart.covid_tracker.model.entities.User
import javax.inject.Inject

class ProfileViewModel @Inject constructor(): ViewModel() {
    var user = User() //temporary value because there is no registration
}