package com.ebookfrenzy.userpage.presentation.random_profile

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebookfrenzy.userpage.R
import com.ebookfrenzy.userpage.common.Resource
import com.ebookfrenzy.userpage.domain.use_case.GetRandomProfileUseCase
import com.ebookfrenzy.userpage.domain.use_case.SaveProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "RandomProfileViewModel"

@HiltViewModel
class RandomProfileViewModel @Inject constructor(
    private val randomProfileUseCase: GetRandomProfileUseCase,
    private val saveProfileUseCase: SaveProfileUseCase,
): ViewModel() {

    private val _state = MutableLiveData<RandomProfileState>()
    val state: LiveData<RandomProfileState> get() = _state

    private val _saveToDbResult = MutableLiveData<Int>()
    val saveToDbResult: LiveData<Int> get() = _saveToDbResult

    init {
        getProfile()
    }

    fun getProfile(){
        randomProfileUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = RandomProfileState(profile = result.data)
                }
                is Resource.Loading -> {
                    _state.value = RandomProfileState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = RandomProfileState(
                        error = result.message ?: "an Unexpected Error occurred"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun saveProfile(){
        _state.value?.profile?.let {profile->
            viewModelScope.launch(Dispatchers.IO){
                try {
                    saveProfileUseCase(profile)
                    _state.postValue(RandomProfileState(profile = profile))
                    _saveToDbResult.postValue(R.string.profile_saved)
                } catch (e: SQLiteConstraintException){
                    _saveToDbResult.postValue(R.string.profile_already_exist)
                } catch (e: Exception){
                    _saveToDbResult.postValue(R.string.unknown_error)
                }
            }

        }

    }

}