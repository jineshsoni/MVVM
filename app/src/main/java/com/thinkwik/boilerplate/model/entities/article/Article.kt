package com.thinkwik.boilerplate.model.entities.article

import com.thinkwik.boilerplate.model.entities.sources.Sources

data class Article(
    val author: String = "",
    val content: String = "",
    val description: String = "",
    val publishedAt: String = "",
    val source: Sources = Sources(),
    val title: String = "",
    val url: String = "",
    val urlToImage: String = ""
)