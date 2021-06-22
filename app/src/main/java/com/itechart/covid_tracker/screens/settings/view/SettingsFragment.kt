package com.itechart.covid_tracker.screens.settings.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.screens.settings.presenter.SettingsPresenter

class SettingsFragment: Fragment() {
    private lateinit var fragment:View
    private lateinit var presenter: SettingsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = inflater.inflate(R.layout.fragment_settings, container, false)
        presenter = SettingsPresenter()

        val sw_notifications = fragment.findViewById<SwitchCompat>(R.id.sw_notifications)
        sw_notifications.isChecked = presenter.settings.notifications
        sw_notifications.setOnCheckedChangeListener { buttonView, isChecked ->
            presenter.settings.notifications = isChecked
        }

        val sw_sounds = fragment.findViewById<SwitchCompat>(R.id.sw_sounds)
        sw_sounds.isChecked = presenter.settings.sounds
        sw_sounds.setOnCheckedChangeListener { buttonView, isChecked ->
            presenter.settings.sounds = isChecked
        }

        return fragment
    }

    companion object{
        fun newInstance(): Fragment {
            return SettingsFragment()
        }
    }
}