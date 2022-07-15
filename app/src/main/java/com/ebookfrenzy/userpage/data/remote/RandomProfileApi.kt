package com.ebookfrenzy.userpage.data.remote

import com.ebookfrenzy.userpage.data.remote.dto.ProfileDTO
import retrofit2.http.GET

interface RandomProfileApi {

    @GET("api")
    suspend fun getRandomProfile(): ProfileDTO
}