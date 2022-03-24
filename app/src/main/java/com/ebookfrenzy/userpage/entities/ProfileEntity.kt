package com.ebookfrenzy.userpage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.text.SimpleDateFormat
import java.util.*


data class ResponseData(
    @Json(name = "results") val profile: List<ProfileEntity>
)

@Entity(tableName = "profiles_table")
data class ProfileEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    @Transient
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: Name,

    @ColumnInfo(name = "location")
    val location: Location,

    @ColumnInfo(name = "date_of_birth")
    val dob: DateOfBirth,

    @ColumnInfo(name = "phone")
    val phone: String,

    @ColumnInfo(name = "pic_url")
    val picture: Picture,
){
    override fun toString(): String {
        val name = name.first + " " + name.last
        val phone = phone
        val coordinates =
            location.coordinates.latitude.toString() + " " + location.coordinates.longitude.toString()
        return "$name\n$phone\n$coordinates"
    }
}

data class Name(
    val first: String,
    val last: String,
){
    override fun toString(): String {
        return "$first $last"
    }
}

data class Coordinates(
    val latitude: Double,
    val longitude: Double,
){
    override fun toString(): String {
        return "$latitude, $longitude"
    }
}

data class DateOfBirth(val date: String){
    override fun toString(): String {
        val formatter = SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss.ss'Z'", Locale.ENGLISH)
        val date = formatter.parse(this.date)?: return "Invalid date"
        val stringFormatter = SimpleDateFormat("dd MMM, yyy", Locale.ENGLISH).format(date)
        return stringFormatter.format(date)

    }

}

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

data class Picture(
    @Json(name = "medium")
    val url: String,
)