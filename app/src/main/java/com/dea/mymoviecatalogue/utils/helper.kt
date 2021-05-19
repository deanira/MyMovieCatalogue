package com.dea.mymoviecatalogue.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.dea.mymoviecatalogue.R

fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load("${Constant.IMAGE_URL}$url")
        .placeholder(R.color.gray)
        .into(this)
}