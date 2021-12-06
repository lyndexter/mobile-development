package com.lyndexter.androiddevelopment.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.lyndexter.androiddevelopment.R
import com.lyndexter.androiddevelopment.presentation.activity.MainActivity

class MainFragment : Fragment(R.layout.fragment_main) {

    private var signInButton: Button? = null
    private var signUpButton: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(view.findViewById(R.id.main_toolbar))
        initializeViews(view)
        signInButton?.setOnClickListener {
            (activity as MainActivity).goToFragment(SignInFragment.newInstance())
        }

        signUpButton?.setOnClickListener {
            (activity as MainActivity).goToFragment(SignUpFragment.newInstance())
        }
    }

    private fun initializeViews(view: View) {
        signInButton = view.findViewById(R.id.sign_in_button)
        signUpButton = view.findViewById(R.id.sign_up_button)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment MainFragment.
         */
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
