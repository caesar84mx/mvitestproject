package com.caesar84mx.mvitestproject.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FactResponse(
    val type: String,
    val value: Fact?
)
