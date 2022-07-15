package com.ebookfrenzy.userpage.data.repositories

import android.util.Log
import com.ebookfrenzy.userpage.data.remote.RandomProfileApi
import com.ebookfrenzy.userpage.data.remote.dto.toProfile
import com.ebookfrenzy.userpage.domain.models.Profile
import com.ebookfrenzy.userpage.domain.repositories.RandomProfileRepository
import javax.inject.Inject

private const val TAG = "ProfileRepository"

class RandomProfileRepositoryImpl @Inject constructor(
   private val api: RandomProfileApi
): RandomProfileRepository {

    override suspend fun getRandomProfile(): Profile {
        try{
            val profile = api.getRandomProfile()
            return profile.toProfile()
        } catch (e: Exception){
            Log.i(TAG, "getRandomProfile: ${e.message}")
            throw e
        }
    }
}