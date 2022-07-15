package com.ebookfrenzy.userpage.data.remote.dto


import com.ebookfrenzy.userpage.domain.models.DateOfBirth
import com.squareup.moshi.Json

data class Dob(
    @Json(name = "age")
    val age: Int,
    @Json(name = "date")
    val date: String
)
fun Dob.toProfileDob(): DateOfBirth {
    return DateOfBirth(date)
}