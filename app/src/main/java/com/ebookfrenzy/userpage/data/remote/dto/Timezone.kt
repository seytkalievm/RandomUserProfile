package com.ebookfrenzy.userpage.data.remote.dto


import com.squareup.moshi.Json

data class Timezone(
    @Json(name = "description")
    val description: String,
    @Json(name = "offset")
    val offset: String
)