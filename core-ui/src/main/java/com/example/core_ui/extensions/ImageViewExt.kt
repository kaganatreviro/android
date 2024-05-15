package com.example.core_ui.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImageWithGlide(url: String) = Glide.with(this).load(url).centerCrop().into(this)
fun ImageView.setImageWithGlide(drawable: Int) = Glide.with(this).load(drawable).centerCrop().into(this)