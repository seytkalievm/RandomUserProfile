package com.ebookfrenzy.userpage.data.remote.dto

import com.squareup.moshi.Json

data class Coordinates(
    @Json(name = "latitude")
    val latitude: String,
    @Json(name = "longitude")
    val longitude: String
)

fun Coordinates.toProfileCoordinates(): com.ebookfrenzy.userpage.domain.models.Coordinates{
    return com.ebookfrenzy.userpage.domain.models.Coordinates(latitude, longitude)
}