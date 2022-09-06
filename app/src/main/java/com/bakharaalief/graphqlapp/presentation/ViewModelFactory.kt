package com.bakharaalief.graphqlapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bakharaalief.graphqlapp.di.Injection
import com.bakharaalief.graphqlapp.domain.usecase.MediaUseCase
import com.bakharaalief.graphqlapp.presentation.characterDetail.MediaDetailViewModel
import com.bakharaalief.graphqlapp.presentation.main.MainViewModel

class ViewModelFactory(
    private val mediaUseCase: MediaUseCase,
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(
                mediaUseCase
            ) as T
            modelClass.isAssignableFrom(MediaDetailViewModel::class.java) -> MediaDetailViewModel(
                mediaUseCase
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
                    Injection.provideMediaUseCase()
                )
            }.also { instance = it }
    }
}