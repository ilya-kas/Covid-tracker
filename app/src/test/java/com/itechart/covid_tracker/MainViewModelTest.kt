package com.itechart.covid_tracker

import android.content.Context
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.di.component.DaggerTestAppComponent
import com.itechart.covid_tracker.di.module.TestDBModule
import com.itechart.covid_tracker.logic.entity.Country
import com.itechart.covid_tracker.screens.main.MainViewModel
import io.mockk.mockkClass
import junit.framework.Assert.*
import org.junit.Test

import org.junit.Before
import javax.inject.Inject

class MainViewModelTest {

    @Inject
    lateinit var viewModel: MainViewModel

    @Before
    fun init(){
        val component = DaggerTestAppComponent.builder()
            .dBModule(TestDBModule(mockkClass(Context::class)))
            .build()
        component.inject(this)

        App.appComponent = component

        //given
        viewModel.countries = listOf(Country(0,"aab", false, null),
            Country(1,"abb", true, null),
            Country(2,"bbb", true, null),
            Country(3,"aabb", false, null))
        viewModel.model.countries = viewModel.countries
    }

    @Test
    fun starred_false_test() {
        assertNotNull(viewModel)

        //given
        viewModel.countries[0].favorite = false

        //when
        viewModel.starred(0)

        //then
        assertEquals(true, viewModel.countries[0].favorite)
    }

    @Test
    fun starred_true_test() {
        assertNotNull(viewModel)

        //given
        viewModel.countries[0].favorite = true

        //when
        viewModel.starred(0)

        //then
        assertEquals(false, viewModel.countries[0].favorite)
    }

    @Test
    fun filter_addALetter_test() {
        assertNotNull(viewModel)

        //given
        viewModel.filter("")

        //when
        viewModel.filter("a")

        //then
        assertEquals(3, viewModel.listLength)
    }

    @Test
    fun filter_removeALetter_test() {
        assertNotNull(viewModel)

        //given
        viewModel.filter("aa")

        //when
        viewModel.filter("a")

        //then
        assertEquals(3, viewModel.listLength)
    }

    @Test
    fun filter_toEmptyFilter_test() {
        assertNotNull(viewModel)

        //given
        viewModel.filter("a")

        //when
        viewModel.filter("")

        //then
        assertEquals(4, viewModel.listLength)
    }
}