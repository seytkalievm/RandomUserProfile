package com.ebookfrenzy.userpage.api

import com.ebookfrenzy.userpage.entities.ProfileEntity
import com.ebookfrenzy.userpage.entities.ResponseData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://randomuser.me/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addConverterFactory(ScalarsConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface RandomProfileApiService{
    @GET("api")
    fun getRandomProfileAsync(): Deferred<ResponseData>
}

object RandomProfileApi{
    val RETROFIT_SERVICE: RandomProfileApiService by lazy {
        retrofit.create(RandomProfileApiService::class.java)
    }
}