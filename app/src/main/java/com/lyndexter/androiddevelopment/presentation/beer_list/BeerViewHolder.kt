package com.lyndexter.androiddevelopment.presentation.beer_list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lyndexter.androiddevelopment.R
import com.lyndexter.androiddevelopment.domain.Beer

private val IMAGE_WITH = 400
private val IMAGE_HEIGHT = 400

class BeerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val firstName: TextView = itemView.findViewById(R.id.first_name)
    private val lastName: TextView = itemView.findViewById(R.id.last_name)
    private val profilePhoto: ImageView = itemView.findViewById(R.id.profile_photo)

    fun bind(beer: Beer) {
        firstName.text = beer.name
        lastName.text = beer.description

        Glide.with(itemView)
            .load(beer.pictureUrl)
            .placeholder(R.drawable.baseline_arrow_back_18)
            .error(R.drawable.common_google_signin_btn_icon_light)
            .fallback(R.drawable.common_google_signin_btn_text_dark_normal)
            .override(IMAGE_WITH, IMAGE_HEIGHT)
            .into(profilePhoto)
    }
}
