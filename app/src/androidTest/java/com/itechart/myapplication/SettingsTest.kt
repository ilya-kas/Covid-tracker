package com.itechart.myapplication

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.itechart.covid_tracker.app_level.dagger.module.DBModule
import com.itechart.covid_tracker.model.Model
import com.itechart.myapplication.di.DaggerTestComponent
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class SettingsTest {
    @Inject
    lateinit var model: Model

    lateinit var appContext: Context

    @Before
    fun init(){
        appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val testComponent = DaggerTestComponent.builder()
            .dBModule(DBModule(appContext))
            .build()
        testComponent.inject(this)
    }

    @After
    fun finally(){

    }

    @Test
    fun appName(){
        assertEquals(appContext.applicationInfo.packageName, "com.itechart.covid_tracker")
    }

    @Test
    fun loadCountries(){
        //assertEquals(1, favoritesRepository.favoritesDAO.getAll().size)
    }
}