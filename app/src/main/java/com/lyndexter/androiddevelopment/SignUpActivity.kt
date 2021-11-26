package com.lyndexter.androiddevelopment

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up.*

private const val MIN_LENGTH_PASSWORD = 8

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setSupportActionBar(sign_up_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val redirectionListener = View.OnClickListener { view ->
            when (view.id) {
                R.id.login_reference -> {
                    startActivity(Intent(this, SignInActivity::class.java))
                }
                R.id.sign_up_button -> {
                    if (validateInputs()) {
                        val authenticationResult = AuthenticationResultDialog()
                        val manager = supportFragmentManager
                        authenticationResult.show(manager, "successSignUp")
                    }
                }
            }
        }
        login_reference.setOnClickListener(redirectionListener)
        sign_up_button.setOnClickListener(redirectionListener)
    }

    private fun validateInputs(): Boolean {
        email_layout?.isErrorEnabled = false
        name_layout?.isErrorEnabled = false
        password_layout?.isErrorEnabled = false
        confirm_password_layout?.isErrorEnabled = false

        var validationIndicator = 0

        if (email?.text.toString().isEmpty()) {
            email_layout?.error = "this field can't be blank"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email?.text.toString())
            .matches()
        ) {
            email_layout?.error = "email is not properly formatted"
        } else {
            validationIndicator++
        }

        if (name?.text.toString().isEmpty()) {
            name_layout?.error = "this field can't be blank"
        } else {
            validationIndicator++
        }

        if (password?.text.toString().isEmpty()) {
            password_layout?.error = "this field can't be blank"
        } else if (password?.text.toString().length < 8) {
            password_layout?.error = "password must be longer than 8 characters"
        } else {
            validationIndicator++
        }

        when {
            confirm_password?.text.toString().isEmpty() -> {
                confirm_password_layout?.error = "this field can't be blank"
            }
            confirm_password?.text.toString().length < 8 -> {
                confirm_password_layout?.error = "password must be longer than 8 characters"
            }
            confirm_password?.text.toString() != password?.text.toString() -> {
                confirm_password_layout?.error = "passwords didn't matches"
            }
            else -> {
                validationIndicator++
            }
        }

        return (validationIndicator == 4)
    }
}
