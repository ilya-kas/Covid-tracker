package com.itechart.covid_tracker.screens.login.view

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.model.accounts.OK
import com.itechart.covid_tracker.model.accounts.WRONG_PASSWORD
import com.itechart.covid_tracker.screens.login.LoginViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginFragment: Fragment() {

    private lateinit var fragment: View
    @Inject
    lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = inflater.inflate(R.layout.fragment_login, container, false)

        val b_login = fragment.findViewById<Button>(R.id.b_sign_in)
        b_login.setOnClickListener {
            val login = fragment.findViewById<EditText>(R.id.et_login).text.toString()
            val password = fragment.findViewById<EditText>(R.id.et_password).text.toString()

            GlobalScope.launch {
                val code = viewModel.loginUser(login, password)
                if (code == OK){
                    val navigation = App.appComponent.getNavigation()
                    val mainHandler = Handler(requireContext().mainLooper)
                    mainHandler.post {
                        navigation.popBackStack()
                        navigation.navigate(R.id.profileFragment)
                    }
                }else
                    when (code){
                        WRONG_PASSWORD -> Handler(requireContext().mainLooper).post {
                            Toast.makeText(context,"Wrong login or password", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        return fragment
    }

    companion object{
        fun newInstance(): Fragment {
            val fragment = LoginFragment()
            App.appComponent.inject(fragment)
            return fragment
        }
    }
}