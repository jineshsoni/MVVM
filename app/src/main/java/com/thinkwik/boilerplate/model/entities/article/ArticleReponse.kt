package com.thinkwik.boilerplate.model.entities.article

import java.io.Serializable

data class ArticleReponse(
    val articles: List<Article> = ArrayList(),
    val status: String = "",
    val totalResults: Int = 0,
    var progress : Boolean = true
):Serializable