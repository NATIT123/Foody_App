package com.example.foodyapplication.ui.main.settings

import android.graphics.PorterDuff
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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

@BindingAdapter("tintColor")
fun setTint(view: ImageView, color: Int) {
    view.setColorFilter(color, PorterDuff.Mode.SRC_IN)
}