package com.ebookfrenzy.userpage.data.remote.dto


import com.squareup.moshi.Json

data class Registered(
    @Json(name = "age")
    val age: Int,
    @Json(name = "date")
    val date: String
)