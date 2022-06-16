package com.caesar84mx.mvitestproject.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Fact(
    val id: Int,
    @Json(name = "joke") val fact: String,
    val categories: Set<String>
)
