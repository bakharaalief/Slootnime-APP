package com.bakharaalief.graphqlapp.di

import com.bakharaalief.graphqlapp.domain.usecase.MediaInteractor
import com.bakharaalief.graphqlapp.domain.usecase.MediaUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @ViewModelScoped
    @Binds
    abstract fun provideMediaUseCase(mediaInteractor: MediaInteractor): MediaUseCase
}