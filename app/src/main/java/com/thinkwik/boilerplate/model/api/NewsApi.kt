package com.thinkwik.boilerplate.model.api

import com.thinkwik.boilerplate.model.entities.article.ArticleReponse
import com.thinkwik.boilerplate.model.entities.sources.NewsSourceResponse
import com.thinkwik.boilerplate.network.KEY
import com.thinkwik.boilerplate.network.VALUE
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {

    @GET(KEY.sources)
    fun getNewsListSourceList(@Query(KEY.country) country: String = VALUE.india): Observable<Response<NewsSourceResponse>>

    @GET(KEY.topHeadlines)
    fun getNewsFromSource(@Query(KEY.sources) newsSource: String): Observable<Response<ArticleReponse>>

    @GET(KEY.everything)
    fun getNewsFromKeyword(@Query(KEY.q) q: String): Observable<Response<ArticleReponse>>
}