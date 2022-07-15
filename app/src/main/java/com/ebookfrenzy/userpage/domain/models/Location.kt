package com.ebookfrenzy.userpage.domain.models

data class Location(
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: Coordinates,
){
    override fun toString(): String {
        return "$city, $state, $country. $postcode"
    }
}