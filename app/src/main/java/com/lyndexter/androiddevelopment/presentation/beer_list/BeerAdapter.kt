package com.lyndexter.androiddevelopment.presentation.beer_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.lyndexter.androiddevelopment.R
import com.lyndexter.androiddevelopment.domain.Beer
import timber.log.Timber

class BeerAdapter : ListAdapter<Beer, BeerViewHolder>(BeerDifficultUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.beer_item, parent, false)
        Timber.d("onCreateViewHolder run")
        return BeerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        Timber.d("onCreateViewHolder bind")
        holder.bind(currentList[position])
    }
}
