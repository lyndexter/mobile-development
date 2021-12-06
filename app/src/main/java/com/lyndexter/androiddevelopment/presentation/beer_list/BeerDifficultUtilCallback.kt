package com.lyndexter.androiddevelopment.presentation.beer_list

import androidx.recyclerview.widget.DiffUtil
import com.lyndexter.androiddevelopment.domain.Beer

class BeerDifficultUtilCallback : DiffUtil.ItemCallback<Beer>() {
    override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
        return oldItem == newItem
    }
}
