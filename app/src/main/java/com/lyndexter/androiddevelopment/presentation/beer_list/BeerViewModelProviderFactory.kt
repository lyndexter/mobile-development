package com.lyndexter.androiddevelopment.presentation.beer_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lyndexter.androiddevelopment.data.BeerRepository
import com.lyndexter.androiddevelopment.data.RetrofitBuilder

class BeerViewModelProviderFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BeerViewModel(BeerRepository(RetrofitBuilder.api)) as T
    }
}
