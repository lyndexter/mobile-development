package com.lyndexter.androiddevelopment

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignInActivity : AppCompatActivity() {

    private var toolbarSignIn: Toolbar? = null
    private var textViewToSignUp: TextView? = null
    private var emailInput: TextInputEditText? = null
    private var passwordInput: TextInputEditText? = null
    private var emailInputLayout: TextInputLayout? = null
    private var passwordInputLayout: TextInputLayout? = null
    private var signInButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        toolbarSignIn = findViewById(R.id.sign_in_toolbar)
        textViewToSignUp = findViewById(R.id.register_reference)
        emailInput = findViewById(R.id.email)
        passwordInput = findViewById(R.id.password)
        emailInputLayout = findViewById(R.id.email_layout)
        passwordInputLayout = findViewById(R.id.password_layout)
        signInButton = findViewById(R.id.sign_in_button)

        setSupportActionBar(toolbarSignIn)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val redirectionListener = View.OnClickListener { view ->
            when (view.id) {
                R.id.register_reference -> {
                    startActivity(Intent(this, SignUpActivity::class.java))
                }
                R.id.sign_in_button -> {
                    if (validateInputs()) {
                        val authenticationResult = AuthenticationResultDialog()
                        val manager = supportFragmentManager
                        authenticationResult.show(manager, "successLogin")
                    }
                }
            }
        }
        textViewToSignUp?.setOnClickListener(redirectionListener)
        signInButton?.setOnClickListener(redirectionListener)
    }

    private fun validateInputs(): Boolean {
        emailInputLayout?.isErrorEnabled = false
        passwordInputLayout?.isErrorEnabled = false
        var validationIndicator = 0
        if (emailInput?.text.toString().isEmpty()) {
            emailInputLayout?.error = "this field can't be blank"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput?.text.toString())
            .matches()
        ) {
            emailInputLayout?.error = "email is not properly formatted"
        } else {
            validationIndicator += 1
        }

        if (passwordInput?.text.toString().isEmpty()) {
            passwordInputLayout?.error = "this field can't be blank"
        } else if (passwordInput?.text.toString().length < 8) {
            passwordInputLayout?.error = "password must be longer than 8 characters"
        } else {
            validationIndicator += 1
        }

        return (validationIndicator == 2)
    }
}
