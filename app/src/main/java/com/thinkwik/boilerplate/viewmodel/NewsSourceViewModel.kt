package com.thinkwik.boilerplate.viewmodel

import androidx.lifecycle.MutableLiveData
import com.thinkwik.boilerplate.model.api.NewsApiRepo
import com.thinkwik.boilerplate.model.entities.sources.NewsSourceResponse
import com.thinkwik.boilerplate.utils.logv

class NewsSourceViewModel : BaseViewModel() {

    private var newsData = MutableLiveData<NewsSourceResponse>()
    private val newsApiRepo = NewsApiRepo()

    fun getSource(){
        newsData = newsApiRepo.getSourceList()
        logv("VM GET SOURCE")
    }


    fun observeSourceList(): MutableLiveData<NewsSourceResponse> {
        return newsData
    }

}