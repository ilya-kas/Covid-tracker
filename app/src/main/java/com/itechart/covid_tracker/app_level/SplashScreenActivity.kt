package com.itechart.covid_tracker.app_level

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.model.FilledCountriesProvider
import com.itechart.covid_tracker.model.database.favorites.FavoritesProvider
import com.itechart.covid_tracker.model.network.CovidStatsProvider
import com.itechart.covid_tracker.screens.main.MainViewModel
import com.itechart.covid_tracker.screens.settings.SettingsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashScreenActivity : AppCompatActivity() {
    @Inject
    lateinit var filledCountriesProvider: FilledCountriesProvider
    @Inject
    lateinit var settingsViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        GlobalScope.launch(Dispatchers.IO) {
            filledCountriesProvider.preload()
            settingsViewModel.loadSettings() //loading settings
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            finish()
        }
    }
}