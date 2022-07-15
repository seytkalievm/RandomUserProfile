package com.ebookfrenzy.userpage.data.remote.dto


import com.ebookfrenzy.userpage.domain.models.ProfilePicture
import com.squareup.moshi.Json

data class Picture(
    @Json(name = "large")
    val large: String,
    @Json(name = "medium")
    val medium: String,
    @Json(name = "thumbnail")
    val thumbnail: String
)

fun Picture.toProfilePicture(): ProfilePicture {
    return ProfilePicture(url = large)
}