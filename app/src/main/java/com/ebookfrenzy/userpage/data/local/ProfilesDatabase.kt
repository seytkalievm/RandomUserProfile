package com.ebookfrenzy.userpage.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ProfileEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ProfilesDatabase: RoomDatabase() {

    abstract val profileDao: ProfileDao

    companion object{
        @Volatile
        private var INSTANCE: ProfilesDatabase? = null

        fun getInstance(context: Context): ProfilesDatabase {
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProfilesDatabase::class.java, "profiles_table")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }

        }
    }

}