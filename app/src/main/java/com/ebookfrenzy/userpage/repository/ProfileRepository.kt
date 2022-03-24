package com.ebookfrenzy.userpage.repository

import androidx.lifecycle.LiveData
import com.ebookfrenzy.userpage.api.RandomProfileApi
import com.ebookfrenzy.userpage.db.ProfilesDatabase
import com.ebookfrenzy.userpage.entities.ProfileEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileRepository(private val database: ProfilesDatabase) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun getProfileAsync() = RandomProfileApi.RETROFIT_SERVICE.getRandomProfileAsync()

    fun insertProfile(profile: ProfileEntity?) {
       coroutineScope.launch{
            database.profileDao.insertProfile(profile)
        }
    }

    fun getAllProfiles():LiveData<List<ProfileEntity>> {
        return database.profileDao.getAllProfiles()
    }

}

