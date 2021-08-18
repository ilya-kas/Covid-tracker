package com.itechart.covid_tracker

import android.content.Context
import com.itechart.covid_tracker.di.component.DaggerTestAppComponent
import com.itechart.covid_tracker.di.module.TestDBModule
import com.itechart.covid_tracker.screens.settings.SettingsViewModel
import io.mockk.mockkClass
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Test

import org.junit.Before
import javax.inject.Inject

class SettingsViewModelTest {

    @Inject
    lateinit var viewModel: SettingsViewModel

    @Before
    fun init(){
        val component = DaggerTestAppComponent.builder()
            .dBModule(TestDBModule(mockkClass(Context::class)))
            .build()
        component.inject(this)
    }

    @Test
    fun setNotificationsState_test() {
        assertNotNull(viewModel)

        //given
        viewModel.model.settings.notifications = false

        //when
        viewModel.setNotificationsState(true)

        //then
        assertEquals(true, viewModel.settings.notifications)
        assertEquals(true, viewModel.model.settings.notifications)
    }
}