package com.thinkwik.boilerplate.model.entities.sources

import java.io.Serializable

data class Sources(
    val category: String = "",
    val country: String = "",
    val description: String = "",
    val id: String = "",
    val language: String = "",
    val name: String = "",
    val url: String = ""
): Serializable