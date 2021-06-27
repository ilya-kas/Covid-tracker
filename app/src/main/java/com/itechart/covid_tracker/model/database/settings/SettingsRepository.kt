package com.itechart.covid_tracker.model.database.settings

import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.model.entities.Country
import com.itechart.covid_tracker.model.entities.Settings
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SettingsRepository {
    var settingsDAO = App.appComponent.getSettingsDAO() //DAO for ROOM DB access

    fun loadSettings(): Settings{ //favorite countries loading from DB
        val loadableSettings = settingsDAO.getSettings()
        if (loadableSettings.isEmpty())
            return Settings(true)
        return Settings(loadableSettings[0].notifications)
    }

    fun save(settings: Settings){
        GlobalScope.launch {
            settingsDAO.insert(LoadableSettings(settings))
        }
    }
}