package com.bakharaalief.graphqlapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bakharaalief.graphqlapp.di.Injection
import com.bakharaalief.graphqlapp.domain.usecase.CharacterUseCase
import com.bakharaalief.graphqlapp.presentation.characterDetail.CharacterDetailViewModel
import com.bakharaalief.graphqlapp.presentation.main.MainViewModel

class ViewModelFactory(
    private val characterUseCase: CharacterUseCase,
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(
                characterUseCase
            ) as T
            modelClass.isAssignableFrom(CharacterDetailViewModel::class.java) -> CharacterDetailViewModel(
                characterUseCase
            ) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideCharacterUseCase()
                )
            }.also { instance = it }
    }
}