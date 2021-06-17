package com.itechart.covid_tracker.view.login_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.presenter.login.LoginPresenter
import com.itechart.covid_tracker.view.main_screen.MainFragment

class EnterFragment: Fragment() {
    private lateinit var fragment: View
    private lateinit var presenter: LoginPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = inflater.inflate(R.layout.fragment_enter, container, false)
        presenter = LoginPresenter()

        val b_sing_in = fragment.findViewById<Button>(R.id.b_sign_in)
        b_sing_in.setOnClickListener {
            parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, MainFragment.newInstance())
                    .commit()
        }

        val b_sing_up = fragment.findViewById<Button>(R.id.b_sign_up)
        b_sing_up.setOnClickListener {
            parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, MainFragment.newInstance())
                    .commit()
        }

        return fragment
    }

    companion object{
        fun newInstance(): Fragment {
            return EnterFragment()
        }
    }
}