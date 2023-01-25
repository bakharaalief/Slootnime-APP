package com.bakhdev.data.di

import com.bakharaalief.domain.repository.IMediaRepository
import com.bakhdev.data.repository.MediaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideMediaRepository(mediaRepository: MediaRepository): IMediaRepository
}