package com.bakharaalief.slootnimeapp.di

import com.bakharaalief.slootnimeapp.data.MediaRepository
import com.bakharaalief.slootnimeapp.domain.repository.IMediaRepository
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