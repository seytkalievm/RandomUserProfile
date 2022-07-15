package com.ebookfrenzy.userpage.data.repositories


import android.util.Log
import com.ebookfrenzy.userpage.data.local.ProfileDao
import com.ebookfrenzy.userpage.data.local.toProfile
import com.ebookfrenzy.userpage.domain.models.Profile
import com.ebookfrenzy.userpage.domain.models.toEntity
import com.ebookfrenzy.userpage.domain.repositories.SavedProfilesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val TAG = "SavedProfileRepository"

class SavedProfilesRepositoryImpl
    @Inject constructor(
        private val dao: ProfileDao
    ): SavedProfilesRepository {

    override suspend fun saveProfile(profile: Profile) {
        Log.i(TAG, "saveProfile: trying to insert profile")
        try {
            dao.insertProfile(profile.toEntity())
        }catch (e: Exception){
            Log.i(TAG, "saveProfile Exception: ${e.message}")
            throw (e)
        }
    }

    override suspend fun deleteProfile(phoneNumber: String) {
        dao.deleteProfile(phoneNumber)
        Log.i(TAG, "deleteProfile: deleted profile")
    }

    override suspend fun getProfile(phoneNumber: String): Profile {
        return dao.getUser(phoneNumber).toProfile()
    }

    override val allSavedProfiles: Flow<List<Profile>>
        get() = dao.getAllProfiles().map { list ->
            list.map { entity -> entity.toProfile() }
        }
}