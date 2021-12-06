package com.lyndexter.androiddevelopment.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lyndexter.androiddevelopment.R
import com.lyndexter.androiddevelopment.presentation.beer_list.BeerAdapter
import com.lyndexter.androiddevelopment.presentation.beer_list.BeerViewModel
import com.lyndexter.androiddevelopment.presentation.beer_list.BeerViewModelProviderFactory
import timber.log.Timber

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    private val viewModel by viewModels<BeerViewModel> { BeerViewModelProviderFactory() }

    private val adapter = BeerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeToolbar(view)
        initialiseRecycleView(view)
        subscribeOnViewModel()

        viewModel.getBeers()
    }

    private fun initializeToolbar(view: View) {
        (activity as AppCompatActivity).setSupportActionBar(view.findViewById(R.id.main_toolbar))
    }

    private fun initialiseRecycleView(view: View) {
        val entityRecycleView: RecyclerView = view.findViewById(R.id.entity_list)
        entityRecycleView.adapter = adapter
        entityRecycleView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    private fun subscribeOnViewModel() {
        viewModel.beerList.observe(this, {
            adapter.submitList(it)
        })

        viewModel.errorMessage.observe(this, { message ->
            Timber.d(message)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = WelcomeFragment()
    }
}
