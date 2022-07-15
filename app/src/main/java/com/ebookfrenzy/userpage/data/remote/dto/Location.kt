package com.ebookfrenzy.userpage.data.remote.dto


import com.squareup.moshi.Json

data class Location(
    @Json(name = "city")
    val city: String,
    @Json(name = "coordinates")
    val coordinates: Coordinates,
    @Json(name = "country")
    val country: String,
    @Json(name = "postcode")
    val postcode: String,
    @Json(name = "state")
    val state: String,
    @Json(name = "street")
    val street: Street,
    @Json(name = "timezone")
    val timezone: Timezone
)

fun Location.toProfileLocation(): com.ebookfrenzy.userpage.domain.models.Location{
    return com.ebookfrenzy.userpage.domain.models.Location(
        city, state, country, postcode, coordinates.toProfileCoordinates()
    )
}