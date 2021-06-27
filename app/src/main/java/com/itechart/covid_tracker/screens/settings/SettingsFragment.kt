package com.itechart.covid_tracker.screens.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import androidx.lifecycle.ViewModelProvider
import com.itechart.covid_tracker.R

class SettingsFragment: Fragment() {
    private lateinit var fragment:View
    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = inflater.inflate(R.layout.fragment_settings, container, false)
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)

        val sw_notifications = fragment.findViewById<SwitchCompat>(R.id.sw_notifications)
        sw_notifications.isChecked = viewModel.settings.notifications
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