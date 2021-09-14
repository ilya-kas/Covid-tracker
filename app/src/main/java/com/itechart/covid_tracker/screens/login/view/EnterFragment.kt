package com.itechart.covid_tracker.screens.login.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.screens.login.LoginViewModel
import javax.inject.Inject

class EnterFragment: Fragment() {
    private lateinit var fragment: View
    @Inject
    lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = inflater.inflate(R.layout.fragment_enter, container, false)
        App.appComponent.inject(this)

        val b_sing_in = fragment.findViewById<Button>(R.id.b_sign_in)
        b_sing_in.setOnClickListener {
            parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, LoginFragment.newInstance())
                    .addToBackStack("login")
                    .commit()
        }

        val b_sing_up = fragment.findViewById<Button>(R.id.b_sign_up)
        b_sing_up.setOnClickListener {
            parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, RegistrationFragment.newInstance())
                    .addToBackStack("registration")
                    .commit()
        }

        return fragment
    }
}