package com.itechart.covid_tracker.screens.login.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.screens.login.LoginViewModel

class RegistrationFragment: Fragment() {
    private lateinit var fragment: View
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = inflater.inflate(R.layout.fragment_register, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        //todo

        return fragment
    }

    companion object{
        fun newInstance(): Fragment {
            return RegistrationFragment()
        }
    }
}