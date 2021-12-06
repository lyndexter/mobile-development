package com.lyndexter.androiddevelopment.presentation.user_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lyndexter.androiddevelopment.data.RetrofitBuilder
import com.lyndexter.androiddevelopment.data.UserRepository

class UserViewModelProviderFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(UserRepository(RetrofitBuilder.api)) as T
    }
}
