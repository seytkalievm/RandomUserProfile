package com.ebookfrenzy.userpage.domain.use_case

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.ebookfrenzy.userpage.domain.models.Profile
import com.ebookfrenzy.userpage.domain.repositories.SavedProfilesRepository
import javax.inject.Inject

class SaveProfileUseCase @Inject constructor(
    private val repository: SavedProfilesRepository
) {
    suspend operator fun invoke(profile: Profile){
        try {
            repository.saveProfile(profile)
        }catch (e: SQLiteConstraintException){
            Log.i("SaveProfileUseCase", "invoke: $e")
            throw (e)
        }
    }
}