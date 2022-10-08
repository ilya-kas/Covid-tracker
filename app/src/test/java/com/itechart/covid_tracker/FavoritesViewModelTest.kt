package com.itechart.covid_tracker

import android.content.Context
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.di.component.DaggerTestAppComponent
import com.itechart.covid_tracker.di.module.TestDBModule
import com.itechart.covid_tracker.logic.entity.Country
import com.itechart.covid_tracker.screens.favorites.FavoritesViewModel
import io.mockk.mockkClass
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Test

import org.junit.Before
import javax.inject.Inject

class FavoritesViewModelTest {

    @Inject
    lateinit var viewModel: FavoritesViewModel

    @Before
    fun init(){
        val component = DaggerTestAppComponent.builder()
            .dBModule(TestDBModule(mockkClass(Context::class)))
            .build()
        component.inject(this)

        App.appComponent = component

        //given
        viewModel.model.countries = listOf(Country(0,"", false, null),
            Country(1,"", true, null),
            Country(2,"", true, null),
            Country(3,"", false, null))
        assertEquals(2, viewModel.listLength)
    }

    @Test
    fun filteredCountries_test() {
        assertNotNull(viewModel)

        //when
        viewModel.model.countries[3].favorite = true

        //then
        assertEquals(3, viewModel.listLength)
    }

    @Test
    fun removed_test(){
        assertNotNull(viewModel)

        //when
        viewModel.itemRemoved(1)

        //then
        assertEquals(1, viewModel.listLength)
    }
}