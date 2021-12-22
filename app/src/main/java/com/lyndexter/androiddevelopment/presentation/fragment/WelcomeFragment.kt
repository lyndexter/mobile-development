package com.lyndexter.androiddevelopment.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lyndexter.androiddevelopment.R
import com.lyndexter.androiddevelopment.presentation.activity.WelcomeActivity
import com.lyndexter.androiddevelopment.presentation.beer_list.BeerAdapter
import com.lyndexter.androiddevelopment.presentation.beer_list.BeerViewModel
import com.lyndexter.androiddevelopment.presentation.beer_list.BeerViewModelProviderFactory
import timber.log.Timber

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {
    private val viewModel: BeerViewModel by activityViewModels { BeerViewModelProviderFactory() }

    private val adapter = BeerAdapter(::onBeerItemClick)
    private var refreshLayout: SwipeRefreshLayout? = null
    private var progressBar: ProgressBar? = null
    private var settingsButton: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeProgressBar(view)
        initializeToolbar(view)
        initialiseRecycleView(view)
        initialiseRefreshLayout(view)
        initializeButton(view)

        subscribeOnViewModel()

        viewModel.getBeers()
    }

    private fun initializeToolbar(view: View) {
        (activity as AppCompatActivity).setSupportActionBar(view.findViewById(R.id.main_toolbar))
    }

    private fun initializeProgressBar(view: View) {
        progressBar = view.findViewById(R.id.progress_bar_welcome_fragment)
    }

    private fun initializeButton(view: View) {
        settingsButton = view.findViewById(R.id.settings_button)
        settingsButton?.setOnClickListener {
            (activity as WelcomeActivity).goToSettingsActivity()
        }
    }

    private fun initialiseRecycleView(view: View) {
        val entityRecycleView: RecyclerView = view.findViewById(R.id.entity_list)
        entityRecycleView.adapter = adapter
        entityRecycleView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    private fun initialiseRefreshLayout(view: View) {
        refreshLayout = view.findViewById(R.id.refresh_layout)
        refreshLayout?.setOnRefreshListener { viewModel.getBeers() }
    }

    private fun subscribeOnViewModel() {
        viewModel.beerList.observe(this, {
            adapter.submitList(it)
            progressBar?.visibility = View.GONE
            refreshLayout?.isRefreshing = false
        })

        viewModel.errorMessage.observe(this, { message ->
            Timber.d(message)
        })
    }

    private fun onBeerItemClick(position: Int) {
        viewModel.beerItemPosition.value = position
        (activity as WelcomeActivity).goToFragment(ItemDetailsFragment.newInstance())
    }

    companion object {
        @JvmStatic
        fun newInstance() = WelcomeFragment()
    }
}
