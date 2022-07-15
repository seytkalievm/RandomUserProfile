package com.ebookfrenzy.userpage.domain.models

data class Coordinates(
    val latitude: String,
    val longitude: String,
){
    override fun toString(): String {
        return "$latitude, $longitude"
    }
}