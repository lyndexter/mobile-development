package com.lyndexter.androiddevelopment.presentation.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.lyndexter.androiddevelopment.R
import com.lyndexter.androiddevelopment.domain.User
import timber.log.Timber

class UserAdapter : ListAdapter<User, UserViewHolder>(UserDifficultUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        Timber.d("onCreateViewHolder run")
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}
