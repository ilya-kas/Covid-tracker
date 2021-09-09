package com.itechart.covid_tracker.model.database.settings

import com.itechart.covid_tracker.model.entities.Settings

interface SettingsProvider {
    fun loadSettings(): Settings
    fun save(settings: Settings)
}