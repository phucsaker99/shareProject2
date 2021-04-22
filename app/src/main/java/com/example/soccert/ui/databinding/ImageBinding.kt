package com.example.soccert.ui.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.soccert.utils.loadImageCircle
import com.example.soccert.utils.loadImageRectangle

@BindingAdapter("app:image", "app:cropCircle", requireAll = false)
fun loadUrlImageCircle(imageView: ImageView, urlImage: String, cropStatus: Boolean = false) {
    if (cropStatus) {
        imageView.loadImageCircle(urlImage)
    } else {
        imageView.loadImageRectangle(urlImage)
    }
}