package com.itechart.covid_tracker.screens.login.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.screens.login.presenter.LoginPresenter
import com.itechart.covid_tracker.screens.main.view.MainFragment

class LoginFragment: Fragment() {
    private lateinit var fragment: View
    private lateinit var presenter: LoginPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = inflater.inflate(R.layout.fragment_login, container, false)
        presenter = LoginPresenter()

        //todo

        return fragment
    }

    companion object{
        fun newInstance(): Fragment {
            return LoginFragment()
        }
    }
}