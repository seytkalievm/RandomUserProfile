package com.ebookfrenzy.userpage.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    @Insert
    fun insertProfile(profile: ProfileEntity?)

    @Query("DELETE FROM profiles_table WHERE phone = :phoneNumber")
    fun deleteProfile(phoneNumber: String)

    @Query("SELECT * FROM profiles_table")
    fun getAllProfiles():Flow<List<ProfileEntity>>

    @Query("SELECT * FROM profiles_table WHERE phone = :phoneNumber")
    fun getUser(phoneNumber: String): ProfileEntity
}