package com.ebookfrenzy.userpage.ui.main

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ebookfrenzy.userpage.UserPageApplication
import com.ebookfrenzy.userpage.entities.ProfileEntity
import com.ebookfrenzy.userpage.repository.ProfileRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserPageViewModel(
    application: Application,
    private val profileRepository: ProfileRepository) : AndroidViewModel(application) {

    private val _profile = MutableLiveData<ProfileEntity>()
    val profile: LiveData<ProfileEntity> get() = _profile

    private val _loadingStatus = MutableLiveData<String>()
    val loadingStatus: LiveData<String> get() = _loadingStatus

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> get() = _status

    private var viewModelJob = Job()
    private var coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getProfile()

    }

    private fun getProfile(){
        startLoading()
        coroutineScope.launch {
            try {
                if (hasInternetConnection()) {
                    val getProfileDeferred = profileRepository.getProfileAsync().await()
                    if (getProfileDeferred.profile.isNotEmpty()) {
                        _profile.value = getProfileDeferred.profile[0]
                        profileRepository.insertProfile(_profile.value)
                        stopLoading("success")
                    }
                } else{
                    stopLoading("error")
                    _status.value = "No internet connection"
                    Log.i("E", "No internet connection")
                }

            }catch (t: Throwable){
                _status.value = t.message.toString()
                Log.i("E", t.message.toString())
                stopLoading("error")
            }catch (e: Exception){
                _status.value = e.message.toString()
                Log.i("E",e.message.toString())
                stopLoading("exception")
            }
        }
    }

    private fun startLoading(){
        _loadingStatus.value = "loading"
    }
    private fun stopLoading(status: String){
        _loadingStatus.value = status
    }

    fun updateProfile(){
        getProfile()
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<UserPageApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

}