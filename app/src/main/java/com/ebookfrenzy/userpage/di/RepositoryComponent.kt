package com.ebookfrenzy.userpage.di

import com.ebookfrenzy.userpage.data.local.ProfileDao
import com.ebookfrenzy.userpage.data.remote.RandomProfileApi
import com.ebookfrenzy.userpage.data.repositories.RandomProfileRepositoryImpl
import com.ebookfrenzy.userpage.data.repositories.SavedProfilesRepositoryImpl
import com.ebookfrenzy.userpage.domain.repositories.RandomProfileRepository
import com.ebookfrenzy.userpage.domain.repositories.SavedProfilesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryComponent {

    @Singleton
    @Provides
    fun provideRandomProfileRepository(api: RandomProfileApi): RandomProfileRepository{
        return RandomProfileRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideSavedProfilesRepository(dao: ProfileDao): SavedProfilesRepository {
        return SavedProfilesRepositoryImpl(dao)
    }
}