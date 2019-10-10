package com.thinkwik.boilerplate.helper

import android.graphics.PorterDuff
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.thinkwik.boilerplate.R
import com.thinkwik.boilerplate.utils.AppConfig


fun ImageView.glide(
    url: String,
    roundedCorners: Int = 0,
    allowProgress: Boolean = true
) {

    var requestOptions = RequestOptions()
    if (roundedCorners > 0) {
        requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(roundedCorners))
    }
    if (allowProgress) {

        val circularProgressDrawable = CircularProgressDrawable(AppConfig.instance)
        circularProgressDrawable.setColorFilter(
            context.resources.getColor(R.color.colorPrimary),
            PorterDuff.Mode.SRC_ATOP
        )
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(AppConfig.instance)
            .load(url)
            .placeholder(circularProgressDrawable)
            .apply(requestOptions)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    } else {
        Glide.with(AppConfig.instance)
            .load(url)
            .placeholder(R.mipmap.ic_launcher)
            .apply(requestOptions)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}


