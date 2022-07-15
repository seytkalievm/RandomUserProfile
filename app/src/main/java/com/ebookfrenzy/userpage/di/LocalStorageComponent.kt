package com.ebookfrenzy.userpage.di

import android.content.Context
import com.ebookfrenzy.userpage.data.local.ProfileDao
import com.ebookfrenzy.userpage.data.local.ProfilesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
object LocalStorageComponent {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ProfilesDatabase{
        return ProfilesDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideProfileDao(db: ProfilesDatabase): ProfileDao{
        return db.profileDao
    }
}