package com.lyndexter.androiddevelopment.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.lyndexter.androiddevelopment.R
import com.lyndexter.androiddevelopment.domain.Beer

private const val PREFS_VAL = "sdsa"
private const val BEER_COUNT = "beer_count"

class ItemDetailsFragment() : Fragment(R.layout.fragment_item_details) {

    private var titleTextView: TextView? = null
    private var descriptionTextView: TextView? = null
    private var imageView: ImageView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeView(view)
    }

    private fun initializeView(view: View) {
    }

    companion object {
        @JvmStatic
        fun newInstance() = ItemDetailsFragment()
    }
}
