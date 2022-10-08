package com.itechart.covid_tracker.screens.profile

import androidx.lifecycle.ViewModel
import com.itechart.covid_tracker.logic.entity.User
import javax.inject.Inject

class ProfileViewModel @Inject constructor(): ViewModel() {
    var user = User() //temporary value because there is no registration
}