package com.example.core_ui.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.core_ui.R

fun ImageView.loadImageWithGlide(url: String) = Glide.with(this).load(url).centerCrop().placeholder(
    R.drawable.ic_logo).into(this)