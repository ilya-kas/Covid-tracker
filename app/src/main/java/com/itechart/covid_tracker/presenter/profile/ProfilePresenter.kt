package com.itechart.covid_tracker.presenter.profile

import com.itechart.covid_tracker.model.Model

class ProfilePresenter {
    var user = Model.loadUser()

    fun updateUserInfo(){
        user = Model.loadUser()
    }
}