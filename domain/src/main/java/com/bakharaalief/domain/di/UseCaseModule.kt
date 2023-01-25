package com.bakharaalief.domain.di

import com.bakharaalief.domain.interactor.MediaInteractor
import com.bakharaalief.domain.usecase.MediaUseCase
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