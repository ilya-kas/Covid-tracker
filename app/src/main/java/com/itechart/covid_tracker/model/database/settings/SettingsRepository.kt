package com.itechart.covid_tracker.model.database.settings

import com.itechart.covid_tracker.logic.entity.Settings
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton                                        //DAO for ROOM DB access
class SettingsRepository @Inject constructor(var settingsDAO: SettingsDAO): SettingsProvider {

    override fun loadSettings(): Settings{ //favorite countries loading from DB
        val loadableSettings = settingsDAO.getSettings()
        if (loadableSettings.isEmpty())
            return Settings(true, true)
        return Settings(loadableSettings.last().notifications, loadableSettings.last().firstLaunch)
    }

    override fun save(settings: Settings){
        GlobalScope.launch {
            settingsDAO.insert(LoadableSettings(settings))
        }
    }
}