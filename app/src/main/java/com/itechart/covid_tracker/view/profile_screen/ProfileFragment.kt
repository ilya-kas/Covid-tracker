package com.itechart.covid_tracker.view.profile_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.presenter.profile.ProfilePresenter

class ProfileFragment: Fragment() {
    private lateinit var fragment:View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = inflater.inflate(R.layout.fragment_profile, container, false)

        val topLabel = fragment.findViewById<TextView>(R.id.top_label)
        topLabel.text = (ProfilePresenter.user.name + " " + ProfilePresenter.user.surname)

        val tv_login = fragment.findViewById<TextView>(R.id.tv_login)
        tv_login.text = ("Login: " + ProfilePresenter.user.login)
        val tv_password = fragment.findViewById<TextView>(R.id.tv_password)
        tv_password.text = ("Password: " + ProfilePresenter.user.password)
        val tv_email = fragment.findViewById<TextView>(R.id.tv_email)
        tv_email.text = ("Email: " + ProfilePresenter.user.email)
        val tv_number = fragment.findViewById<TextView>(R.id.tv_number)
        tv_number.text = ("Number: " + ProfilePresenter.user.number)
        val tv_index = fragment.findViewById<TextView>(R.id.tv_index)
        tv_index.text = ("Index: " + ProfilePresenter.user.index)
        val tv_address = fragment.findViewById<TextView>(R.id.tv_address)
        tv_address.text = ("Address: " + ProfilePresenter.user.address)

        return fragment
    }

    companion object{
        fun newInstance(): Fragment {
            ProfilePresenter.updateUserInfo()
            return ProfileFragment()
        }
    }
}