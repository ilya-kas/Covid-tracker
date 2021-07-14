package com.itechart.covid_tracker.screens.settings

import androidx.lifecycle.ViewModel
import com.itechart.covid_tracker.model.Model
import com.itechart.covid_tracker.model.entities.Settings
import javax.inject.Inject

class SettingsViewModel @Inject constructor(): ViewModel() {

    var settings = Model.settings

    fun checkedNotifications(isChecked: Boolean){
        settings.notifications = isChecked
        Model.saveSettings(settings)
    }
}