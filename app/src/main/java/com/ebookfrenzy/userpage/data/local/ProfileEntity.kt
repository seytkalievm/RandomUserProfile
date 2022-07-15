package com.ebookfrenzy.userpage.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ebookfrenzy.userpage.domain.models.*


@Entity(tableName = "profiles_table")
data class ProfileEntity(
    @ColumnInfo(name = "name")
    val name: Name,

    @ColumnInfo(name = "location")
    val location: Location,

    @ColumnInfo(name = "date_of_birth")
    val dob: DateOfBirth,

    @PrimaryKey
    @ColumnInfo(name = "phone")
    val phone: String,

    @ColumnInfo(name = "pic_url")
    val picture: ProfilePicture,
){
    override fun toString(): String {
        val name = name.toString()
        val phone = phone
        val coordinates = location.coordinates.toString()
        return "$name\n$phone\n$coordinates"
    }
}

fun ProfileEntity.toProfile(): Profile{
    return Profile(
        name = this.name,
        location = this.location,
        dob = this.dob,
        phone = this.phone,
        picture = this.picture
    )
}
