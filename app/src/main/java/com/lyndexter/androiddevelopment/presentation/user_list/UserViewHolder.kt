package com.lyndexter.androiddevelopment.presentation.user_list

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lyndexter.androiddevelopment.R
import com.lyndexter.androiddevelopment.domain.User

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val firstName: TextView = itemView.findViewById(R.id.first_name)
    private val lastName: TextView = itemView.findViewById(R.id.last_name)

    fun bind(user: User) {
        firstName.text = user.firstName
        lastName.text = user.lastName
    }
}
