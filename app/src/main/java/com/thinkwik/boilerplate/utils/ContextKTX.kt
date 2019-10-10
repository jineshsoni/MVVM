package com.thinkwik.boilerplate.utils

import android.animation.ObjectAnimator
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.thinkwik.boilerplate.R


fun toast(message: String) {
    Toast.makeText(AppConfig.instance, message, Toast.LENGTH_LONG).show()
}

fun ProgressBar.addProgress(percent: Int, duration: Int = 300) {
    val animation = ObjectAnimator.ofInt(this, "progress", percent)
    animation.duration = duration.toLong()
    animation.interpolator = AccelerateInterpolator()
    animation.start()
    this.progress = percent
}

fun Activity.resourceString(string: Int): String {
    return this.resources.getString(string)
}

fun Fragment.resourceString(string: Int): String {
    return activity!!.resources.getString(string)
}

fun Dialog.resourceString(string: Int): String {
    return this.context.resources.getString(string)
}

fun Activity.resourceDrawable(image: Int): Drawable {
    return this.resources.getDrawable(image)
}

fun Fragment.resourceDrawable(image: Int): Drawable {
    return this.resources.getDrawable(image)
}

fun Activity.resourceColor(color: Int): Int {
    return this.resources.getColor(color)
}

fun Fragment.resourceColor(color: Int): Int {
    return this.resources.getColor(color)
}

fun Context.layoutManager(orientation: Int): LinearLayoutManager {
    return LinearLayoutManager(this, orientation, false)
}

fun Context.gridLayoutManager(spanCount: Int, orientation: Int): GridLayoutManager {
    return GridLayoutManager(this, spanCount, orientation, false)
}

fun Context.startActivityWithAnim(
    intent: Intent,
    isFromTop: Boolean,
    isWithAnimation: Boolean = true
) {
    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
    if (isWithAnimation) {
        if (isFromTop) appearTopAnimation(this as Activity)
        else appearLeftAnimation(this as Activity)
    } else
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

    startActivity(intent)
}

fun Activity.startActivityWithView(intent: Intent, view: View) {
    val options = ViewCompat.getTransitionName(view)?.let {
        ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, it)
    }
    startActivity(intent, options?.toBundle())
}

fun Activity.setSharedElementEnterTransitionTime(time: Long = 250) {
    window.enterTransition = null
    window.exitTransition = null
    getWindow().sharedElementEnterTransition = android.transition.ChangeBounds().setDuration(time)
}

fun Activity.fullScreenActivity(color: Int = Color.TRANSPARENT) {
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    window.statusBarColor = Color.TRANSPARENT
}

fun Fragment.startActivityWithAnim(intent: Intent, isFromTop: Boolean) {
    this.activity!!.startActivity(intent)
    if (isFromTop) appearTopAnimation(this.activity!!)
    else appearLeftAnimation(this.activity!!)
}

fun Dialog.startActivityWithAnim(intent: Intent, isFromTop: Boolean) {
    this.context!!.startActivity(intent)
}

fun appearLeftAnimation(a: Activity) {
    a.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}

fun disappearLeftAnimation(a: Activity) {
    a.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
}

fun appearTopAnimation(a: Activity) {
    a.overridePendingTransition(R.anim.slide_up, R.anim.no_change)
}

fun disappearTopAnimation(a: Activity) {
    a.overridePendingTransition(0, R.anim.slide_down)
}