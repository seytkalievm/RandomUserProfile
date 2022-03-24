package com.ebookfrenzy.userpage.db

import androidx.room.TypeConverter
import com.ebookfrenzy.userpage.entities.*

class Converters {

    @TypeConverter
    fun fromName(name: Name): String{
        return "${name.first} ${name.last}"
    }

    @TypeConverter
    fun toName(string: String): Name{
        val space = string.indexOf(' ')
        val first = string.substring(0, space-1)
        val last = string.substring(space, string.length)
        return Name(first, last)
    }

    @TypeConverter
    fun fromLocation(location: Location): String{
        val result = "${location.city},${location.state},${location.country},${location.postcode},"
        val coordinates = "${location.coordinates.latitude},${location.coordinates.longitude}"
        return result + coordinates
    }

    @TypeConverter
    fun toLocation(string: String): Location {
        val location = string.split(",").toTypedArray()
        val coordinates = Coordinates(
            location[4].toDouble(),
            location[5].toDouble())
        return Location(
            city = location[0],
            state = location[1],
            country = location[2],
            postcode = location[3],
            coordinates = coordinates)
    }

    @TypeConverter
    fun fromDob(dob: DateOfBirth): String{
        return dob.date
    }

    @TypeConverter
    fun toDob(string: String): DateOfBirth{
        return DateOfBirth(string)
    }

    @TypeConverter
    fun fromPicture(pic: Picture): String{
        return pic.url
    }

    @TypeConverter
    fun toPicture(string: String): Picture{
        return Picture(string)
    }


}