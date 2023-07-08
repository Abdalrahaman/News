package com.example.task1.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.task1.R

object BindingAdapters {

    @BindingAdapter("loadImage")
    @JvmStatic
    fun bindImage(imgView: ImageView, imgUrl: String?) {
        imgView.load(imgUrl) {
            placeholder(R.drawable.ic_launcher_background)
            error(R.drawable.ic_launcher_background)
        }
    }
}