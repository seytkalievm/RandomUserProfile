package com.ebookfrenzy.userpage.presentation.saved_profiles

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ebookfrenzy.userpage.domain.models.Profile
import com.ebookfrenzy.userpage.domain.use_case.GetSavedProfilesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SavedProfilesViewModel @Inject constructor(
    private val getSavedProfilesUseCase: GetSavedProfilesUseCase
): ViewModel(){

    val profiles: LiveData<List<Profile>> get() = getSavedProfilesUseCase()


}