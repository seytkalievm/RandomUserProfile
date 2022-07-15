package com.ebookfrenzy.userpage.domain.models

import java.text.SimpleDateFormat
import java.util.*


data class DateOfBirth(val date: String){
    override fun toString(): String {
        val formatter = SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss.ss'Z'", Locale.ENGLISH)
        val date = formatter.parse(this.date)?: return "Invalid date"
        val stringFormatter = SimpleDateFormat("dd MMM, yyy", Locale.ENGLISH).format(date)
        return stringFormatter.format(date)
    }
}