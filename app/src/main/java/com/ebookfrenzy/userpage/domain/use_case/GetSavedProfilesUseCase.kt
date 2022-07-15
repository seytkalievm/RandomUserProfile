package com.ebookfrenzy.userpage.domain.use_case

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.ebookfrenzy.userpage.domain.models.Profile
import com.ebookfrenzy.userpage.domain.repositories.SavedProfilesRepository
import javax.inject.Inject

class GetSavedProfilesUseCase @Inject constructor(
    private val repo: SavedProfilesRepository
) {
    operator fun invoke(): LiveData<List<Profile>>{
        return repo.allSavedProfiles.asLiveData()
    }
}