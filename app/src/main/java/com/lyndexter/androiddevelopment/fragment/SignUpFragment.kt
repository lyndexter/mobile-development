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
import com.lyndexter.androiddevelopment.viewmodel.SignUpViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private var toolbarSignUp: Toolbar? = null
    private var textViewToSignIn: TextView? = null
    private var nameInput: TextInputEditText? = null
    private var emailInput: TextInputEditText? = null
    private var passwordInput: TextInputEditText? = null
    private var confirmPasswordInput: TextInputEditText? = null
    private var nameInputLayout: TextInputLayout? = null
    private var emailInputLayout: TextInputLayout? = null
    private var passwordInputLayout: TextInputLayout? = null
    private var confirmPasswordInputLayout: TextInputLayout? = null
    private var signUpButton: Button? = null

    private var viewModel: SignUpViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeToolbar(view)
        initializeViews(view)
        initializeViewModel()
        signUpButton?.setOnClickListener {
            clearInputFieldsError()
            viewModel?.signUp(
                nameInput?.text.toString(), emailInput?.text.toString(),
                passwordInput?.text.toString(), confirmPasswordInput?.text.toString()
            )
        }
        textViewToSignIn?.setOnClickListener {
            (activity as MainActivity).goToFragment(SignInFragment.newInstance())
        }
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        viewModel?.nameError?.observe(viewLifecycleOwner) { error ->
            nameInputLayout?.error = error
        }

        viewModel?.emailError?.observe(viewLifecycleOwner) { error ->
            emailInputLayout?.error = error
        }

        viewModel?.passwordError?.observe(viewLifecycleOwner) { error ->
            passwordInputLayout?.error = error
        }

        viewModel?.confirmPasswordError?.observe(viewLifecycleOwner) { error ->
            confirmPasswordInputLayout?.error = error
        }
        viewModel?.user?.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                clearInputFieldsText()
                displaySuccess()
                (activity as MainActivity).goToWelcomeActivity()
            } else {
                Toast.makeText(
                    activity, "Failed create this user",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun displaySuccess() {
        activity?.let {
            AlertDialog.Builder(it)
                .setTitle("Success")
                .setMessage("You have successfully signed up!")
                .show()
        }
    }

    private fun initializeToolbar(view: View) {
        toolbarSignUp = view.findViewById(R.id.sign_up_toolbar)
        toolbarSignUp?.setNavigationOnClickListener {
            (activity as MainActivity).goToFragment(MainFragment.newInstance())
        }
    }

    private fun initializeViews(view: View) {
        textViewToSignIn = view.findViewById(R.id.login_reference)

        signUpButton = view.findViewById(R.id.sign_up_button)

        nameInputLayout = view.findViewById(R.id.name_layout)
        nameInput = view.findViewById(R.id.name)

        emailInputLayout = view.findViewById(R.id.email_layout)
        emailInput = view.findViewById(R.id.email)

        passwordInputLayout = view.findViewById(R.id.password_layout)
        passwordInput = view.findViewById(R.id.password)

        confirmPasswordInputLayout = view.findViewById(R.id.confirm_password_layout)
        confirmPasswordInput = view.findViewById(R.id.confirm_password)
    }

    private fun clearInputFieldsError() {
        nameInputLayout?.error = null
        emailInputLayout?.error = null
        passwordInputLayout?.error = null
        confirmPasswordInputLayout?.error = null
    }

    private fun clearInputFieldsText() {
        nameInput?.text?.clear()
        emailInput?.text?.clear()
        passwordInput?.text?.clear()
        confirmPasswordInput?.text?.clear()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SignUpFragment.
         */
        @JvmStatic
        fun newInstance() = SignUpFragment()
    }
}
