package com.ebookfrenzy.userpage.domain.models

import com.ebookfrenzy.userpage.data.local.ProfileEntity

data class Profile(
    val name: Name,
    val location: Location,
    val dob: DateOfBirth,
    val phone: String,
    val picture: ProfilePicture,
){
    override fun toString(): String {
        val name = name.first + " " + name.last
        val phone = phone
        val coordinates =
            location.coordinates.latitude.toString() + " " + location.coordinates.longitude.toString()
        return "$name\n$phone\n$coordinates"
    }
}

fun Profile.toEntity(): ProfileEntity{
    return ProfileEntity(
        name = this.name,
        location = this.location,
        dob = this.dob,
        phone = this.phone,
        picture = this.picture
    )
}
