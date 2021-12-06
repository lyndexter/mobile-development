package com.lyndexter.androiddevelopment.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lyndexter.androiddevelopment.R

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    private val auth = Firebase.auth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeToolbar(view)
    }

    override fun onResume() {
        super.onResume()
    }

    private fun initializeToolbar(view: View) {
        (activity as AppCompatActivity).setSupportActionBar(view.findViewById(R.id.main_toolbar))
    }

    companion object {
        @JvmStatic
        fun newInstance() = WelcomeFragment()
    }
}
