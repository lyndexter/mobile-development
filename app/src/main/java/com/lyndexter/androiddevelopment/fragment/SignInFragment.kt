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
import com.lyndexter.androiddevelopment.viewmodel.SignInViewModel
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Use the [SignInFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignInFragment : Fragment() {

    private var toolbarSignIn: Toolbar? = null
    private var textViewToSignUp: TextView? = null
    private var emailInput: TextInputEditText? = null
    private var passwordInput: TextInputEditText? = null
    private var emailInputLayout: TextInputLayout? = null
    private var passwordInputLayout: TextInputLayout? = null
    private var signInButton: Button? = null

    private var viewModel: SignInViewModel? = null

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
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeToolbar(view)
        initializeViews(view)
        initializeViewModel()
        signInButton?.setOnClickListener {
            clearInputFieldsError()
            try {
                viewModel?.signIn(emailInput?.text.toString(), passwordInput?.text.toString())
            } catch (e: IllegalStateException) {
                Timber.w(e.message!!)
            }
        }
        textViewToSignUp?.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, SignUpFragment.newInstance())?.commit()
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
                startActivity(Intent(attachedContext, WelcomeActivity::class.java))
            } else {
                Toast.makeText(
                    attachedContext, "Incorrect email or password",
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
         * @return A new instance of fragment SignInFragment.
         */
        @JvmStatic
        fun newInstance() =
            SignInFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private fun clearInputFieldsError() {
        emailInputLayout?.error = null
        passwordInputLayout?.error = null
    }

    private fun displaySuccess() {
        clearInputFieldsText()
        val builder: AlertDialog.Builder = AlertDialog.Builder(attachedContext)
        builder.setTitle("Success")
        builder.setMessage("You have successfully signed in!")
        builder.show()
//        val authenticationResult = AuthenticationResultDialog()
//        val manager = supportFragmentManager
//        authenticationResult.show(manager, "successLogin")
    }

    private fun initializeToolbar(view: View) {
        toolbarSignIn = view.findViewById(R.id.sign_in_toolbar)
        toolbarSignIn?.setNavigationOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, MainFragment.newInstance())?.commit()
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
}
