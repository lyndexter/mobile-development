package com.lyndexter.androiddevelopment.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.lyndexter.androiddevelopment.R
import com.lyndexter.androiddevelopment.domain.Beer
import com.lyndexter.androiddevelopment.presentation.activity.WelcomeActivity
import com.lyndexter.androiddevelopment.presentation.beer_list.BeerViewModel
import com.lyndexter.androiddevelopment.presentation.beer_list.BeerViewModelProviderFactory
import timber.log.Timber

private const val IMAGE_WITH = 400
private const val IMAGE_HEIGHT = 400

class ItemDetailsFragment() : Fragment(R.layout.fragment_item_details) {

    private val viewModel: BeerViewModel by activityViewModels { BeerViewModelProviderFactory() }

    private var titleTextView: TextView? = null
    private var descriptionTextView: TextView? = null
    private var imageView: ImageView? = null
    private var toolbarItemDetails: Toolbar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeToolbar(view)
        initializeView(view)
        setViewValue(view)
    }

    private fun initializeView(view: View) {
        titleTextView = view.findViewById(R.id.title_text_view)
        descriptionTextView = view.findViewById(R.id.description_text_view)
        imageView = view.findViewById(R.id.image)
    }

    private fun setViewValue(view: View) {
        val beer: Beer? = viewModel.getBeer()
        titleTextView?.text = beer?.name
        descriptionTextView?.text = beer?.description
        imageView?.let {
            Glide.with(view)
                .load(beer?.pictureUrl)
                .placeholder(R.drawable.baseline_arrow_back_18)
                .error(R.drawable.common_google_signin_btn_icon_light)
                .fallback(R.drawable.common_google_signin_btn_text_dark_normal)
                .override(IMAGE_WITH, IMAGE_HEIGHT)
                .into(it)
        }
    }

    private fun initializeToolbar(view: View) {
        toolbarItemDetails = view.findViewById(R.id.item_details_toolbar)
        toolbarItemDetails?.setNavigationOnClickListener {
            (activity as WelcomeActivity).goToFragment(WelcomeFragment.newInstance())
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ItemDetailsFragment()
    }
}
