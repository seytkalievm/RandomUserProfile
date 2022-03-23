package com.ebookfrenzy.userpage.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ebookfrenzy.userpage.repository.ProfileRepository

class UserPageViewModelProviderFactory (
    val application: Application,
    val profileRepository: ProfileRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserPageViewModel(application, profileRepository) as T
    }
}