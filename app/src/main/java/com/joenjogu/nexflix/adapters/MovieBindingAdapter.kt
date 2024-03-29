package com.joenjogu.nexflix.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joenjogu.nexflix.R
import com.joenjogu.nexflix.utils.convertDoubleRatingToString

@BindingAdapter("setAdapter")
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    this.run {
        this.setHasFixedSize(true)
        this.adapter = adapter
    }
}

@BindingAdapter("setImageUrl")
fun ImageView.bindImageUrl(imageUrl: String?) {
    if (imageUrl != null && imageUrl.isNotBlank()) {
        Glide.with(this.context)
            .load(imageUrl)
            .placeholder(R.drawable.loading_placeholder)
            .into(this)
    }
}

@BindingAdapter("setRatingText")
fun TextView.convertDoubleToString(number: Double) {
    this.text = convertDoubleRatingToString(number)
}

@BindingAdapter("setDateToYear")
fun TextView.reduceDateToYear(date: String) {
    this.text = date.substring(0, 4)
}
