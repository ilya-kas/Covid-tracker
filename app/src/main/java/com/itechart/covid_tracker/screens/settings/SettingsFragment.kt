package com.itechart.covid_tracker.screens.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.databinding.FragmentSettingsBinding
import javax.inject.Inject

class SettingsFragment: Fragment() {
    private lateinit var fragment:View
    @Inject
    lateinit var viewModel: SettingsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        App.appComponent.injectSettingsFragment(this)

        val binding: FragmentSettingsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false) //data binding
        fragment = binding.root

        val sw_notifications = fragment.findViewById<SwitchCompat>(R.id.sw_notifications)
        binding.state = viewModel.settings.notifications
        sw_notifications.setOnCheckedChangeListener { _, isChecked ->
            viewModel.checkedNotifications(isChecked)
        }

        return fragment
    }

    companion object{
        fun newInstance(): Fragment {
            return SettingsFragment()
        }
    }
}