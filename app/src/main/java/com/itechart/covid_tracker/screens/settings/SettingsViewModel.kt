package com.itechart.covid_tracker.screens.settings

import androidx.lifecycle.ViewModel
import com.itechart.covid_tracker.model.database.settings.SettingsProvider
import com.itechart.covid_tracker.logic.entity.Settings
import javax.inject.Inject
import javax.inject.Singleton

@Singleton //not to be connected to activity's lifecycle
class SettingsViewModel @Inject constructor(private val settingsProvider: SettingsProvider): ViewModel() {

    var settings = Settings(true, true)

    /**
     * suspend is to force use this method in coroutine
     */
    suspend fun loadSettings(){
        settings = settingsProvider.loadSettings()
    }

    fun saveSettings(){
        settingsProvider.save(settings)
    }

    fun setNotificationsState(isChecked: Boolean){
        settings.notifications = isChecked
        settingsProvider.save(settings)
    }
}