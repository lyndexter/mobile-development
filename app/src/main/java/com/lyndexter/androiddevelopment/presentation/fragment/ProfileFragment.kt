package com.lyndexter.androiddevelopment.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.textfield.TextInputEditText
import com.lyndexter.androiddevelopment.R
import com.lyndexter.androiddevelopment.presentation.activity.SettingsActivity
import com.lyndexter.androiddevelopment.presentation.beer_list.BeerViewModel
import com.lyndexter.androiddevelopment.presentation.beer_list.BeerViewModelProviderFactory

class ProfileFragment() : Fragment(R.layout.fragment_item_details) {

    private val viewModel: BeerViewModel by activityViewModels { BeerViewModelProviderFactory() }

    private var toolbarSignIn: Toolbar? = null
    private var spinner: Spinner? = null
    private var emailInput: TextInputEditText? = null
    private var nameInput: TextInputEditText? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        emailInput?.setText("ewrewtferw@gmail.com")
        nameInput?.setText("ewrewtfe")
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
    }

    private fun initializeToolbar(view: View) {
        toolbarSignIn = view.findViewById(R.id.item_details_toolbar)
        toolbarSignIn?.setNavigationOnClickListener {
            (activity as SettingsActivity).goToWelcomeActivity()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}
