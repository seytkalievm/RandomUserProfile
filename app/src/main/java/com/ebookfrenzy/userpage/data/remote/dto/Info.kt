package com.ebookfrenzy.userpage.data.remote.dto


import com.squareup.moshi.Json

data class Info(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: Int,
    @Json(name = "seed")
    val seed: String,
    @Json(name = "version")
    val version: String
)