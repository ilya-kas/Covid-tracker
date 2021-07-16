package com.itechart.covid_tracker.app_level

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.model.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    val model = App.appComponent.getModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch(Dispatchers.IO) {
            model.loadCountries() //to load countries list from server
            model.loadFavorites() //loading favorite countries
            model.loadSettings() //loading settings
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            finish()
        }
    }
}