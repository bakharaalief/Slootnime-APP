package com.bakharaalief.graphqlapp

import com.bakharaalief.graphqlapp.data.MediaRepository
import com.bakharaalief.graphqlapp.data.ApolloInstance

object Injection {

    private val apolloInstance = ApolloInstance.get()

    fun provideRepository(): MediaRepository = MediaRepository(apolloInstance)
}