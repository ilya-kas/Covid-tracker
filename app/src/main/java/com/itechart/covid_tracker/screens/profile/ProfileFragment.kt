package com.itechart.covid_tracker.screens.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.itechart.covid_tracker.R

class ProfileFragment: Fragment() {
    private lateinit var fragment:View
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = inflater.inflate(R.layout.fragment_profile, container, false)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        val topLabel = fragment.findViewById<TextView>(R.id.top_label)
        topLabel.text = (viewModel.user.name + " " + viewModel.user.surname)

        val tv_login = fragment.findViewById<TextView>(R.id.tv_login)
        tv_login.text = ("Login: " + viewModel.user.login)
        val tv_password = fragment.findViewById<TextView>(R.id.tv_password)
        tv_password.text = ("Password: " + viewModel.user.password)
        val tv_email = fragment.findViewById<TextView>(R.id.tv_email)
        tv_email.text = ("Email: " + viewModel.user.email)
        val tv_number = fragment.findViewById<TextView>(R.id.tv_number)
        tv_number.text = ("Number: " + viewModel.user.number)
        val tv_index = fragment.findViewById<TextView>(R.id.tv_index)
        tv_index.text = ("Index: " + viewModel.user.index)
        val tv_address = fragment.findViewById<TextView>(R.id.tv_address)
        tv_address.text = ("Address: " + viewModel.user.address)

        return fragment
    }

    companion object{
        fun newInstance(): Fragment {
            return ProfileFragment()
        }
    }
}