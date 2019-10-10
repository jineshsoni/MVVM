package com.thinkwik.boilerplate.viewmodel

import androidx.lifecycle.MutableLiveData
import com.thinkwik.boilerplate.model.api.NewsApiRepo
import com.thinkwik.boilerplate.model.entities.article.ArticleReponse
import com.thinkwik.boilerplate.model.entities.sources.NewsSourceResponse
import com.thinkwik.boilerplate.utils.logv

class NewsArticleViewModel : BaseViewModel() {

    private var newsData = MutableLiveData<ArticleReponse>()
    private val newsApiRepo = NewsApiRepo()

    fun getArticles(source: String){
        newsData = newsApiRepo.getNewsList(source)
    }


    fun observeSourceList(): MutableLiveData<ArticleReponse> {
        return newsData
    }

}