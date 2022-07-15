package com.ebookfrenzy.userpage.domain.use_case

import com.ebookfrenzy.userpage.domain.repositories.SavedProfilesRepository
import javax.inject.Inject

class DeleteProfileUseCase @Inject constructor(
    private val repo: SavedProfilesRepository
) {
    suspend operator fun invoke(phoneNumber: String){
        repo.deleteProfile(phoneNumber)
    }

}