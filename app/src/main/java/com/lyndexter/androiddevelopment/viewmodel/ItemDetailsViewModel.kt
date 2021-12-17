package com.lyndexter.androiddevelopment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lyndexter.androiddevelopment.presentation.validator.EmailValidator
import com.lyndexter.androiddevelopment.presentation.validator.PasswordValidator
import timber.log.Timber
import java.lang.IllegalStateException

class ItemDetailsViewModel : ViewModel() {
    val emailError = MutableLiveData<String>()
    val passwordError = MutableLiveData<String>()

}
