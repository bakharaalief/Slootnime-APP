package com.bakharaalief.graphqlapp.di

import com.bakharaalief.graphqlapp.data.CharacterRepository
import com.bakharaalief.graphqlapp.data.network.ApolloInstance

object Injection {

    private val apolloInstance = ApolloInstance.get()

    fun provideRepository(): CharacterRepository = CharacterRepository(apolloInstance)
}