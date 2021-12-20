package com.lyndexter.androiddevelopment.presentation.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
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

private const val PREFS_VAL = "sdsa"
private const val BEER_COUNT = "beer_count"

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {
    private val viewModel: BeerViewModel by activityViewModels { BeerViewModelProviderFactory() }

    private val adapter = BeerAdapter(::onBeerItemClick)
    private var refreshLayout: SwipeRefreshLayout? = null
    private var sharedPreferences: SharedPreferences? = null
    private var progressBar: ProgressBar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeProgressBar(view)
        initializeToolbar(view)
        initialiseRecycleView(view)
        initialiseRefreshLayout(view)
        subscribeOnViewModel()

        sharedPreferences = activity?.getSharedPreferences(PREFS_VAL, Context.MODE_PRIVATE)

        viewModel.getBeers()
    }

    private fun initializeToolbar(view: View) {
        (activity as AppCompatActivity).setSupportActionBar(view.findViewById(R.id.main_toolbar))
    }

    private fun initializeProgressBar(view: View) {
        progressBar = view.findViewById(R.id.progress_bar_welcome_fragment)
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
            sharedPreferences?.edit {
                putInt(BEER_COUNT, it.size)
            }
            Timber.d("items found: %s", sharedPreferences?.getInt(BEER_COUNT, -1))
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
