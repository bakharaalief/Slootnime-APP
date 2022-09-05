package com.bakharaalief.graphqlapp.di

import com.bakharaalief.graphqlapp.data.CharacterRepository
import com.bakharaalief.graphqlapp.data.network.ApolloInstance
import com.bakharaalief.graphqlapp.domain.usecase.CharacterInteractor
import com.bakharaalief.graphqlapp.domain.usecase.CharacterUseCase

object Injection {

    private val apolloInstance = ApolloInstance.get()

    private fun provideCharacterRepository(): CharacterRepository = CharacterRepository.getInstance(
        apolloInstance
    )

    fun provideCharacterUseCase(): CharacterUseCase = CharacterInteractor(
        provideCharacterRepository()
    )
}