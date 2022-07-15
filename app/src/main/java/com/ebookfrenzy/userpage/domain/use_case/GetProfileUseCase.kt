package com.ebookfrenzy.userpage.domain.use_case

import com.ebookfrenzy.userpage.domain.models.Profile
import com.ebookfrenzy.userpage.domain.repositories.SavedProfilesRepository
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
   private val savedProfilesRepository: SavedProfilesRepository
) {

    suspend operator fun invoke(phoneNumber: String): Profile {
        return savedProfilesRepository.getProfile(phoneNumber)

    }
}