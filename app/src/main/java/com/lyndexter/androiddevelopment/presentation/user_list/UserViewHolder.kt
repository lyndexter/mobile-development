package com.lyndexter.androiddevelopment.presentation.user_list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lyndexter.androiddevelopment.R
import com.lyndexter.androiddevelopment.domain.User

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val firstName: TextView = itemView.findViewById(R.id.first_name)
    private val lastName: TextView = itemView.findViewById(R.id.last_name)
    private val profilePhoto: ImageView = itemView.findViewById(R.id.profile_photo)

    fun bind(user: User) {
        firstName.text = user.firstName
        lastName.text = user.lastName

        Glide.with(itemView) // 2
            .load(user.pictureUrl) // 3
            .centerCrop() // 4
            .placeholder(R.drawable.baseline_arrow_back_18) // 5
            .error(R.drawable.common_google_signin_btn_icon_light) // 6
            .fallback(R.drawable.common_google_signin_btn_text_dark_normal) // 7
            .into(profilePhoto) // 8
    }
}
