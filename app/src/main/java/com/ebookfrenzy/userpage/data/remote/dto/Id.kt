package com.ebookfrenzy.userpage.data.remote.dto


import com.squareup.moshi.Json

data class Id(
    @Json(name = "name")
    val name: String?,
    @Json(name = "value")
    val value: String?
)