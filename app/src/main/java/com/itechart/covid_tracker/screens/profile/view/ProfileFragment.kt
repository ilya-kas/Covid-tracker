package com.itechart.covid_tracker.screens.profile.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.screens.profile.presenter.ProfilePresenter

class ProfileFragment: Fragment() {
    private lateinit var fragment:View
    private lateinit var presenter: ProfilePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = inflater.inflate(R.layout.fragment_profile, container, false)
        presenter = ProfilePresenter()

        val topLabel = fragment.findViewById<TextView>(R.id.top_label)
        topLabel.text = (presenter.user.name + " " + presenter.user.surname)

        val tv_login = fragment.findViewById<TextView>(R.id.tv_login)
        tv_login.text = ("Login: " + presenter.user.login)
        val tv_password = fragment.findViewById<TextView>(R.id.tv_password)
        tv_password.text = ("Password: " + presenter.user.password)
        val tv_email = fragment.findViewById<TextView>(R.id.tv_email)
        tv_email.text = ("Email: " + presenter.user.email)
        val tv_number = fragment.findViewById<TextView>(R.id.tv_number)
        tv_number.text = ("Number: " + presenter.user.number)
        val tv_index = fragment.findViewById<TextView>(R.id.tv_index)
        tv_index.text = ("Index: " + presenter.user.index)
        val tv_address = fragment.findViewById<TextView>(R.id.tv_address)
        tv_address.text = ("Address: " + presenter.user.address)

        return fragment
    }

    companion object{
        fun newInstance(): Fragment {
            return ProfileFragment()
        }
    }
}