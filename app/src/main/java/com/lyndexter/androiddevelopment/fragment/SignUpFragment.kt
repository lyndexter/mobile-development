package com.lyndexter.androiddevelopment.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.lyndexter.androiddevelopment.activity.WelcomeActivity
import com.lyndexter.androiddevelopment.viewmodel.SignUpViewModel
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment() {

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

    private lateinit var attachedContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        attachedContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeToolbar(view)
        initializeViews(view)
        initializeViewModel()
        signUpButton?.setOnClickListener {
            clearInputFieldsError()
            try {
                viewModel?.signUp(
                    nameInput?.text.toString(), emailInput?.text.toString(),
                    passwordInput?.text.toString(), confirmPasswordInput?.text.toString()
                )
//                clearInputFieldsText()
//                displaySuccess()
//                startActivity(Intent(attachedContext, WelcomeActivity::class.java))
            } catch (e: IllegalStateException) {
                Timber.w(e.message!!)
            }
        }
        textViewToSignIn?.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, SignInFragment.newInstance())?.commit()
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
                startActivity(Intent(attachedContext, WelcomeActivity::class.java))
            } else {
                Toast.makeText(
                    attachedContext, "Failed create this user",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SignUpFragment.
         */
        @JvmStatic
        fun newInstance() =
            SignUpFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private fun displaySuccess() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(attachedContext)
        builder.setTitle("Success")
        builder.setMessage("You have successfully signed up!")
        builder.show()
    }

    private fun initializeToolbar(view: View) {
        toolbarSignUp = view.findViewById(R.id.sign_up_toolbar)
        toolbarSignUp?.setNavigationOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, MainFragment.newInstance())?.commit()
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
}
