package com.thinkwik.boilerplate.network

import android.content.Intent
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.thinkwik.boilerplate.BuildConfig
import com.thinkwik.boilerplate.utils.AppConfig
import com.thinkwik.boilerplate.view.MainActivity
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object API {
    fun getClient(
        apiType: APIType = APIType.AUTHCODE,
        baseUrl: String = BuildConfig.BASE_URL
    ): Retrofit {
        val client = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            var request = chain.request()
            request = when (apiType) {
                APIType.AUTHCODE -> {
                    request.newBuilder()
                        .addHeader(KEY.authorization, VALUE.authCode)
                        .addHeader(KEY.clientId, BuildConfig.client_id)
                        .addHeader(KEY.clientSecret, BuildConfig.client_secret)
                        .build()
                }
                APIType.NOAUTH -> {
                    request.newBuilder()
                        .addHeader(KEY.clientId, BuildConfig.client_id)
                        .addHeader(KEY.clientSecret, BuildConfig.client_secret)
                        .build()
                }
            }
            val response = chain.proceed(request)

            if (response.code() == 401) {
                val i = Intent(AppConfig.instance, MainActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                AppConfig.instance.startActivity(i)
                System.exit(2)
                return@Interceptor response
            }
            response
        })
            .addNetworkInterceptor(StethoInterceptor())
            .connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }
}

enum class APIType {
    AUTHCODE,
    NOAUTH
}