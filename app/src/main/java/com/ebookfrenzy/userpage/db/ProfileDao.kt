package com.ebookfrenzy.userpage.db

import androidx.room.Dao
import androidx.room.Insert
import com.ebookfrenzy.userpage.entities.ProfileEntity

@Dao
interface ProfileDao {
    @Insert
    fun insertProfile(profile: ProfileEntity?)

}