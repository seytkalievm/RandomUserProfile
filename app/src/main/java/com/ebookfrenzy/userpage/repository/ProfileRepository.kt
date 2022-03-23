package com.ebookfrenzy.userpage.repository

import com.ebookfrenzy.userpage.api.RandomProfileApi
import com.ebookfrenzy.userpage.db.ProfilesDatabase
import com.ebookfrenzy.userpage.entities.ProfileEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileRepository(private val database: ProfilesDatabase) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)


    fun getProfileAsync() = RandomProfileApi.RETROFIT_SERVICE.getRandomProfileAsync()

    suspend fun insertProfile(profile: ProfileEntity?) {
        withContext(Dispatchers.IO) {
            database.profileDao.insertProfile(profile)
        }
    }

}

