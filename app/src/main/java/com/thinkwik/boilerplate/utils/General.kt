package com.thinkwik.boilerplate.utils

import android.util.Log
import com.thinkwik.boilerplate.BuildConfig

private const val tag = "Custom-Log"

fun logw(msg: String) {
    if (BuildConfig.ENABLE_LOG) Log.w(tag, msg)
}

fun logi(msg: String) {
    if (BuildConfig.ENABLE_LOG) Log.i(tag, msg)
}

fun logv(msg: String) {
    if (BuildConfig.ENABLE_LOG) Log.v(tag, msg)
}

fun loge(msg: String) {
    if (BuildConfig.ENABLE_LOG) Log.e(tag, msg)
}