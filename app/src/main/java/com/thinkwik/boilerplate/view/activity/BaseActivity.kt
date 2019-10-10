package com.thinkwik.boilerplate.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thinkwik.boilerplate.R

abstract class BaseActivity : AppCompatActivity() {
    abstract fun init()
}
