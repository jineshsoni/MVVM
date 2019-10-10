package com.thinkwik.boilerplate.model.entities.sources

import java.io.Serializable

data class NewsSourceResponse(
    val sources: List<Sources> = ArrayList(),
    val status: String = ""
): Serializable