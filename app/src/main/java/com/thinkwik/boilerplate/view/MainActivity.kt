package com.thinkwik.boilerplate.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thinkwik.boilerplate.R
import com.thinkwik.boilerplate.helper.IntentHelper
import com.thinkwik.boilerplate.helper.IntentHelper.redirectToSourceList
import com.thinkwik.boilerplate.model.api.NewsApiRepo
import com.thinkwik.boilerplate.view.activity.NewsSourceActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        redirectToSourceList()
        finish()
    }
}
