package com.ebookfrenzy.userpage.domain.models

data class Name(
    val first: String,
    val last: String,
){
    override fun toString(): String {
        return "$first $last"
    }
}