package com.itechart.covid_tracker.app_level

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itechart.covid_tracker.model.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch(Dispatchers.IO) {
            Model.initModel() //to init ROOM
            Model.loadCountries() //to load countries list from server
            Model.loadFavorites() //loading favorite countries
            Model.loadSettings() //loading settings
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            finish()
        }
    }
}