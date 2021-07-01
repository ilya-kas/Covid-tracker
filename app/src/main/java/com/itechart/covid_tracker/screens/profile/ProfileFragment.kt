package com.itechart.covid_tracker.screens.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.databinding.FragmentProfileBinding
import com.itechart.covid_tracker.databinding.FragmentSettingsBinding

class ProfileFragment: Fragment() {
    private lateinit var fragment:View
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: FragmentProfileBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false) //data binding
        fragment = binding.root

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        binding.user = viewModel.user

        return fragment
    }

    companion object{
        fun newInstance(): Fragment {
            return ProfileFragment()
        }
    }
}