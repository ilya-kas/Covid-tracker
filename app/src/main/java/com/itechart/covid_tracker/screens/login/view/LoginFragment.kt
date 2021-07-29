package com.itechart.covid_tracker.screens.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Scope
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.screens.login.LoginViewModel


private const val GOOGLE_AUTH_INTENT_CODE = 123

class LoginFragment: Fragment() {

    private lateinit var fragment: View
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = inflater.inflate(R.layout.fragment_login, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        val b_google_sign_in = fragment.findViewById<View>(R.id.b_google_sign_in)
        b_google_sign_in.setOnClickListener {
            /*val intent = AccountPicker.newChooseAccountIntent(AccountPicker.AccountChooserOptions.Builder()
                                                                .setAllowableAccountsTypes(listOf("com.google"))
                                                                .build())
            startActivityForResult(intent, GOOGLE_AUTH_INTENT_CODE)*/
        }

        return fragment
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GOOGLE_AUTH_INTENT_CODE && resultCode == RESULT_OK){
            val accountName = data?.extras?.get(AccountManager.KEY_ACCOUNT_NAME) ?: "NONE"
            Log.i("google logging", "Selected account: $accountName")
        }
        super.onActivityResult(requestCode, resultCode, data)
    }*/

    companion object{
        fun newInstance(): Fragment {
            return LoginFragment()
        }
    }
}