package com.thinkwik.boilerplate.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.thinkwik.boilerplate.network.KEY
import com.thinkwik.boilerplate.utils.startActivityWithAnim
import com.thinkwik.boilerplate.view.activity.NewsArticlesActivity
import com.thinkwik.boilerplate.view.activity.NewsSourceActivity

object IntentHelper {

    fun Context.redirectToSourceList() {
        this.startActivityWithAnim(
            Intent(this, NewsSourceActivity::class.java),
            false
        )
    }

    fun Activity.redirectToSourceListArticleList(source: String) {
        this.startActivityWithAnim(
            Intent(this, NewsArticlesActivity::class.java)
                .putExtra(KEY.sources, source),
            false
        )
    }
}