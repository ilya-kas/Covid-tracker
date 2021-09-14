package com.itechart.covid_tracker.screens.settings

import androidx.lifecycle.ViewModel
import com.itechart.covid_tracker.model.database.settings.SettingsProvider
import com.itechart.covid_tracker.model.entities.Settings
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(val settingsProvider: SettingsProvider): ViewModel() {

    var settings = Settings(true)

    fun loadSettings(){
        GlobalScope.launch {
            settings = settingsProvider.loadSettings()
        }
    }

    fun setNotificationsState(isChecked: Boolean){
        settings.notifications = isChecked
        settingsProvider.save(settings)
    }
}