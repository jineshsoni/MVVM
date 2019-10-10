package com.thinkwik.boilerplate.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.facebook.stetho.Stetho


class AppConfig : Application() {

    init {
        instance = this
    }

    companion object {
        lateinit var instance: AppConfig
    }


    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    override fun onCreate() {
        super.onCreate()
//        FlowManager.init(this)
//        FirebaseDatabase.getInstance().setPersistenceEnabled(false)false
        instance = this
        Stetho.initializeWithDefaults(this)
    }


}
