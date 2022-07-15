package com.ebookfrenzy.userpage.presentation.profile

import android.util.Log
import androidx.lifecycle.*
import com.ebookfrenzy.userpage.domain.models.Profile
import com.ebookfrenzy.userpage.domain.use_case.DeleteProfileUseCase
import com.ebookfrenzy.userpage.domain.use_case.GetProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ProfileViewModel"
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val deleteProfileUseCase: DeleteProfileUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _profile = MutableLiveData<Profile>()
    val profile: LiveData<Profile> get() = _profile


    private val _deleted = MutableLiveData<Boolean>()
    val deleted: LiveData<Boolean> get() = _deleted


    init {
        val phoneNumber = savedStateHandle.get<String>("phoneNumber")
        Log.i(TAG, "init: $phoneNumber")
        phoneNumber?.let {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    _profile.postValue(getProfileUseCase(phoneNumber))
                } catch (e: Exception){
                    Log.i(TAG, "init : ${e.message}")
                }
            }
        }
    }

    fun deleteProfile(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                _profile.value?.phone.let {
                    deleteProfileUseCase(it!!)
                    _deleted.postValue(true)
                }
            } catch (e: Exception){
                Log.i(TAG, "deleteProfile: ${e.message}")
            }
        }
    }


}