package com.bakharaalief.graphqlapp.di

import com.bakharaalief.graphqlapp.data.MediaRepository
import com.bakharaalief.graphqlapp.data.network.ApolloInstance
import com.bakharaalief.graphqlapp.domain.usecase.MediaInteractor
import com.bakharaalief.graphqlapp.domain.usecase.MediaUseCase

object Injection {

    private val apolloInstance = ApolloInstance.get()

    private fun provideMediaRepository(): MediaRepository = MediaRepository.getInstance(
        apolloInstance
    )

    fun provideMediaUseCase(): MediaUseCase = MediaInteractor(
        provideMediaRepository()
    )
}