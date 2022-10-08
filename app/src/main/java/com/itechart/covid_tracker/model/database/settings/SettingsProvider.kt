package com.itechart.covid_tracker.model.database.settings

import com.itechart.covid_tracker.logic.entity.Settings

interface SettingsProvider {
    fun loadSettings(): Settings
    fun save(settings: Settings)
}