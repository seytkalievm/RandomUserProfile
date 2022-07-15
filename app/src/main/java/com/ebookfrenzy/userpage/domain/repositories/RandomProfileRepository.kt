package com.ebookfrenzy.userpage.domain.repositories

import com.ebookfrenzy.userpage.domain.models.Profile

interface RandomProfileRepository {
    suspend fun getRandomProfile(): Profile
}