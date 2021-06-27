package com.itechart.covid_tracker.screens.settings

import androidx.lifecycle.ViewModel
import com.itechart.covid_tracker.model.Model

class SettingsViewModel: ViewModel() {
    var settings = Model.loadSettings()
}