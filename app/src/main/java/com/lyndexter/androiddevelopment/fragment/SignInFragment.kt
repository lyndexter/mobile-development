package com.lyndexter.androiddevelopment.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.lyndexter.androiddevelopment.R
import com.lyndexter.androiddevelopment.activity.MainActivity
import com.lyndexter.androiddevelopment.viewmodel.SignInViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [SignInFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private var toolbarSignIn: Toolbar? = null
    private var textViewToSignUp: TextView? = null
    private var emailInput: TextInputEditText? = null
    private var passwordInput: TextInputEditText? = null
    private var emailInputLayout: TextInputLayout? = null
    private var passwordInputLayout: TextInputLayout? = null
    private var signInButton: Button? = null

    private var viewModel: SignInViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeToolbar(view)
        initializeViews(view)
        initializeViewModel()
        signInButton?.setOnClickListener {
            clearInputFieldsError()
            viewModel?.signIn(emailInput?.text.toString(), passwordInput?.text.toString())
        }
        textViewToSignUp?.setOnClickListener {
            (activity as MainActivity).goToFragment(SignUpFragment.newInstance())
        }
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        viewModel?.emailError?.observe(viewLifecycleOwner) { error ->
            emailInputLayout?.error = error
        }
        viewModel?.passwordError?.observe(viewLifecycleOwner) { error ->
            passwordInputLayout?.error = error
        }
        viewModel?.user?.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                clearInputFieldsText()
                displaySuccess()
                (activity as MainActivity).goToWelcomeActivity()
            } else {
                Toast.makeText(
                    activity, "Incorrect email or password",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun clearInputFieldsError() {
        emailInputLayout?.error = null
        passwordInputLayout?.error = null
    }

    private fun displaySuccess() {
        clearInputFieldsText()
        activity?.let {
            AlertDialog.Builder(it)
                .setTitle("Success")
                .setMessage("You have successfully signed in!")
                .show()
        }
    }

    private fun initializeToolbar(view: View) {
        toolbarSignIn = view.findViewById(R.id.sign_in_toolbar)
        toolbarSignIn?.setNavigationOnClickListener {
            (activity as MainActivity).goToFragment(MainFragment.newInstance())
        }
    }

    private fun initializeViews(view: View) {
        textViewToSignUp = view.findViewById(R.id.register_reference)

        signInButton = view.findViewById(R.id.sign_in_button)

        emailInputLayout = view.findViewById(R.id.email_layout)
        emailInput = view.findViewById(R.id.email)

        passwordInputLayout = view.findViewById(R.id.password_layout)
        passwordInput = view.findViewById(R.id.password)
    }

    private fun clearInputFieldsText() {
        emailInput?.text?.clear()
        passwordInput?.text?.clear()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SignInFragment.
         */
        @JvmStatic
        fun newInstance() = SignInFragment()
    }
}
