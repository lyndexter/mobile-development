package com.lyndexter.androiddevelopment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lyndexter.androiddevelopment.validator.ConfirmPasswordValidator
import com.lyndexter.androiddevelopment.validator.EmailValidator
import com.lyndexter.androiddevelopment.validator.NameValidator
import com.lyndexter.androiddevelopment.validator.PasswordValidator
import timber.log.Timber
import java.lang.IllegalStateException

class SignUpViewModel : ViewModel() {

    val nameError = MutableLiveData<String>()
    val emailError = MutableLiveData<String>()
    val passwordError = MutableLiveData<String>()
    val confirmPasswordError = MutableLiveData<String>()
    val user = MutableLiveData<FirebaseUser>()
    private val auth = Firebase.auth

    fun signUp(name: String?, email: String?, password: String?, confirmedPassword: String?) {
        val isSignUpFieldsValid = validateSignUpFields(name, email, password, confirmedPassword)
        if (isSignUpFieldsValid) {
            auth.createUserWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Timber.d("createUserWithEmail:success")
                        user.value = auth.currentUser
                    } else {
                        Timber.w("createUserWithEmail:failure ${task.exception}")
                    }
                }
        } else {
            Timber.w("createUserWithEmail:failure, invalid input")
        }
    }

    private fun validateSignUpFields(
        name: String?,
        email: String?,
        password: String?,
        confirmedPassword: String?
    ): Boolean {
        var isValidName = true
        var isValidEmail = true
        var isValidPassword = true
        var doPasswordsMatch = true

        try {
            NameValidator.validate(name)
        } catch (e: IllegalStateException) {
            nameError.value = e.message
            isValidName = false
        }
        try {
            EmailValidator.validate(email)
        } catch (e: IllegalStateException) {
            emailError.value = e.message
            isValidEmail = false
        }
        try {
            PasswordValidator.validate(password)
        } catch (e: IllegalStateException) {
            passwordError.value = e.message
            isValidPassword = false
        }

        try {
            ConfirmPasswordValidator.validate(password, confirmedPassword)
        } catch (e: IllegalStateException) {
            confirmPasswordError.value = e.message
            doPasswordsMatch = false
        }

        return isValidName && isValidEmail && isValidPassword && doPasswordsMatch
    }
}
