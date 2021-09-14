package com.itechart.covid_tracker.screens.login.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.screens.login.LoginViewModel
import javax.inject.Inject

class RegistrationFragment: Fragment() {
    private lateinit var fragment: View
    @Inject
    lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = inflater.inflate(R.layout.fragment_register, container, false)

        //todo

        return fragment
    }

    companion object{
        fun newInstance(): Fragment {
            val fragment = RegistrationFragment()
            App.appComponent.inject(fragment)
            return fragment
        }
    }
}