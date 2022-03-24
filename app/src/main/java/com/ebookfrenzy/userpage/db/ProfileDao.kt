package com.ebookfrenzy.userpage.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ebookfrenzy.userpage.entities.ProfileEntity

@Dao
interface ProfileDao {
    @Insert
    fun insertProfile(profile: ProfileEntity?)

    @Query("SELECT * FROM profiles_table")
    fun getAllProfiles():LiveData<List<ProfileEntity>>
}