package com.ebookfrenzy.userpage.data.remote.dto

import com.squareup.moshi.Json

data class Name(
    @Json(name = "first")
    val first: String,
    @Json(name = "last")
    val last: String,
    @Json(name = "title")
    val title: String
)
fun Name.toProfileName(): com.ebookfrenzy.userpage.domain.models.Name{
    return com.ebookfrenzy.userpage.domain.models.Name(first, last)
}