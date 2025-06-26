package com.example.foodyapplication.common

import android.graphics.PorterDuff
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.foodyapplication.BuildConfig
import com.example.foodyapplication.R

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, path: Int?) {
    if (path != null && path != 0) {
        Glide.with(view.context)
            .load(path)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error_image)
            )
            .into(view)
    } else {
        view.setImageResource(R.drawable.placeholder)
    }
}

@BindingAdapter("loadAvatar")
fun loadAvatar(view: ImageView, path: String?) {
    if (path != null) {
        var pathImage = path
        if (path.toString() == "default.jpg") {
            pathImage = BuildConfig.BASE_URL_IMAGE + path
        }
        Glide.with(view.context)
            .load(pathImage)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.ic_account)
            )
            .into(view)
    } else {
        view.setImageResource(R.drawable.placeholder)
    }
}

@BindingAdapter("tintColor")
fun setTint(view: ImageView, color: Int) {
    view.setColorFilter(color, PorterDuff.Mode.SRC_IN)
}


