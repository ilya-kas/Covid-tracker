package com.itechart.covid_tracker.screens.settings

import androidx.lifecycle.ViewModel
import com.itechart.covid_tracker.model.Model
import com.itechart.covid_tracker.model.entities.Settings
import javax.inject.Inject

class SettingsViewModel @Inject constructor(val model: Model): ViewModel() {

    var settings = model.settings

    fun setNotificationsState(isChecked: Boolean){
        settings.notifications = isChecked
        model.saveSettings(settings)
    }
}