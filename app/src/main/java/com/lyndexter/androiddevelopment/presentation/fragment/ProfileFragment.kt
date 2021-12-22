package com.lyndexter.androiddevelopment.presentation.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.Toolbar
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lyndexter.androiddevelopment.R
import com.lyndexter.androiddevelopment.presentation.activity.SettingsActivity
import com.lyndexter.androiddevelopment.presentation.beer_list.BeerViewModel
import com.lyndexter.androiddevelopment.presentation.beer_list.BeerViewModelProviderFactory
import timber.log.Timber

private const val PREFS_FILE_NAME = "user_data"
private const val LANGUAGE_NAME = "language"
private const val USER_NAME = "name"

class ProfileFragment() : Fragment(R.layout.fragment_profile) {

    private val viewModel: BeerViewModel by activityViewModels { BeerViewModelProviderFactory() }

    private var toolbarSignIn: Toolbar? = null
    private var spinner: Spinner? = null
    private var emailInput: TextInputEditText? = null
    private var nameInput: TextInputEditText? = null
    private var sharedPreferences: SharedPreferences? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = activity?.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)

        initializeToolbar(view)
        initializeViews(view)
        setViewsValue(view)
    }

    private fun initializeViews(view: View) {
        emailInput = view.findViewById(R.id.email_edit_text)
        nameInput = view.findViewById(R.id.name_edit_text)
        spinner = view.findViewById(R.id.language_spinner)
    }

    private fun setViewsValue(view: View) {
        emailInput?.setText(Firebase.auth.currentUser?.email)
        nameInput?.setText(sharedPreferences?.getString(USER_NAME, "Default"))
        activity?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.language_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner?.adapter = adapter
            }
        }
        spinner?.setSelection(
            resources.getStringArray(R.array.language_array)
                .indexOf(sharedPreferences?.getString(LANGUAGE_NAME, "English"))
        )
    }

    private fun initializeToolbar(view: View) {
        toolbarSignIn = view.findViewById(R.id.item_details_toolbar)
        toolbarSignIn?.setNavigationOnClickListener {
            sharedPreferences?.edit {
                putString(USER_NAME, nameInput?.text?.toString())
                putString(LANGUAGE_NAME, spinner?.selectedItem?.toString())
            }
            emailInput?.text?.toString()?.let {
                Firebase.auth.currentUser?.updateEmail(it)?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Timber.d("updateEmail:success")
                    } else {
                        Timber.w("updateEmail:failure ${task.exception}}")
                    }
                }
            }
            (activity as SettingsActivity).goToWelcomeActivity()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}
