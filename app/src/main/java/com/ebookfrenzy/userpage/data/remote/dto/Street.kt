package com.ebookfrenzy.userpage.data.remote.dto


import com.squareup.moshi.Json

data class Street(
    @Json(name = "name")
    val name: String,
    @Json(name = "number")
    val number: String
)