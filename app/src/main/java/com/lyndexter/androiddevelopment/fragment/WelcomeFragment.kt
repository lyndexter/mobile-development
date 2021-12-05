package com.lyndexter.androiddevelopment.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lyndexter.androiddevelopment.R

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    private val auth = Firebase.auth
    private var textView: TextView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = view.findViewById(R.id.welcome_text)
        ("Welcome " + (auth.currentUser?.email)).also { textView?.text = it }
        initializeToolbar(view)
    }

    override fun onResume() {
        super.onResume()
        ("Welcome " + (auth.currentUser?.email)).also { textView?.text = it }
    }

    private fun initializeToolbar(view: View) {
        (activity as AppCompatActivity).setSupportActionBar(view.findViewById(R.id.main_toolbar))
    }

    companion object {
        @JvmStatic
        fun newInstance() = WelcomeFragment()
    }
}
