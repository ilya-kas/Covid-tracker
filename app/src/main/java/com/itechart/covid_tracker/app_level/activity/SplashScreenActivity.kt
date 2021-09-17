package com.itechart.covid_tracker.app_level.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.model.FilledCountriesProvider
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

        GlobalScope.launch {
            settingsViewModel.loadSettings() //loading settings
            if (settingsViewModel.settings.firstLaunch){
                settingsViewModel.settings.firstLaunch = false
                settingsViewModel.saveSettings()
                startActivity(Intent(this@SplashScreenActivity, OnBoardingActivity::class.java))
                finish()
            }else{
                filledCountriesProvider.preload()
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                finish()
            }
        }

    }
}