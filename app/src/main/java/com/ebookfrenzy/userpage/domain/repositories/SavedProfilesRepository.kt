package com.ebookfrenzy.userpage.domain.repositories

import com.ebookfrenzy.userpage.domain.models.Profile
import kotlinx.coroutines.flow.Flow

interface SavedProfilesRepository {

    suspend fun saveProfile(profile: Profile)

    suspend fun deleteProfile(phoneNumber: String)

    suspend fun getProfile(phoneNumber: String): Profile

    val allSavedProfiles: Flow<List<Profile>>

}