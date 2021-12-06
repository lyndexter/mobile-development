package com.lyndexter.androiddevelopment.presentation.user_list

import androidx.recyclerview.widget.DiffUtil
import com.lyndexter.androiddevelopment.domain.User

class UserDifficultUtilCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}
