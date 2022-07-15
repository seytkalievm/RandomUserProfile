package com.ebookfrenzy.userpage.presentation.random_profile

import com.ebookfrenzy.userpage.domain.models.Profile

data class RandomProfileState (
    val isLoading: Boolean = false,
    val profile: Profile? = null,
    val error: String? = null,
)