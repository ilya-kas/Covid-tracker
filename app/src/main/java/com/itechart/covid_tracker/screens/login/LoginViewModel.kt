package com.itechart.covid_tracker.screens.login

import androidx.lifecycle.ViewModel
import com.itechart.covid_tracker.model.accounts.AccountsManager
import com.itechart.covid_tracker.model.accounts.OK
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val accountsManager: AccountsManager): ViewModel() {

    suspend fun loginUser(login: String, password: String): Int = accountsManager.logIn(login, hash(password))

    private fun hash(string: String): String{
        return string //todo
    }
}