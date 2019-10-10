package com.thinkwik.boilerplate.model.api

import android.annotation.SuppressLint
import com.thinkwik.boilerplate.model.entities.article.ArticleReponse
import com.thinkwik.boilerplate.model.entities.sources.NewsSourceResponse
import com.thinkwik.boilerplate.network.API
import com.thinkwik.boilerplate.network.APIType
import com.thinkwik.boilerplate.network.genObserver
import com.thinkwik.boilerplate.utils.logv
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import androidx.lifecycle.MutableLiveData


class NewsApiRepo {

    @SuppressLint("CheckResult")
    fun getSourceList() : MutableLiveData<NewsSourceResponse> {
        val newsData = MutableLiveData<NewsSourceResponse>()

        API.getClient(APIType.AUTHCODE)
            .create(NewsApi::class.java)
            .getNewsListSourceList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(
                genObserver(NewsSourceResponse::class.java, success = { dataObj ->
                    newsData.value = dataObj
                }, error = { msg, code ->
                    newsData.value = null
                })
            )

        return newsData
    }

    @SuppressLint("CheckResult")
    fun getNewsList(source: String) : MutableLiveData<ArticleReponse> {
        val newsData = MutableLiveData<ArticleReponse>()

        API.getClient(APIType.AUTHCODE)
            .create(NewsApi::class.java)
            .getNewsFromSource(source)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(
                genObserver(ArticleReponse::class.java, success = { dataObj ->
                    logv(" Success == $dataObj")
                    dataObj.progress = false
                    newsData.value = dataObj
                }, error = { msg, code ->
                    logv(" Error  == $msg && Code == $code")
                    newsData.value = null
                })
            )
        return newsData
    }

    @SuppressLint("CheckResult")
    fun getSearch(keyword: String) {
        API.getClient(APIType.AUTHCODE)
            .create(NewsApi::class.java)
            .getNewsFromKeyword(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(
                genObserver(ArticleReponse::class.java, success = { dataObj ->
                    logv(" Success == $dataObj")
                }, error = { msg, code ->
                    logv(" Error  == $msg && Code == $code")
                })
            )
    }
}