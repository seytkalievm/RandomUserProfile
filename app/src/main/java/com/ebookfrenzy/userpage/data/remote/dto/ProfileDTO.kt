package com.ebookfrenzy.userpage.data.remote.dto


import com.ebookfrenzy.userpage.domain.models.Profile
import com.squareup.moshi.Json

data class ProfileDTO(
    @Json(name = "info")
    val info: Info,
    @Json(name = "results")
    val results: List<Result>
)
fun ProfileDTO.toProfile(): Profile {
    val res = this.results[0]
    return Profile(
        name = res.name.toProfileName(),
        location = res.location.toProfileLocation(),
        dob = res.dob.toProfileDob(),
        phone =  res.phone,
        picture = res.picture.toProfilePicture(),
    )
}