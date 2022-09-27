package com.bakharaalief.slootnimeapp.domain.model

data class MediaById(
    val date: Date,
    val bannerImage: String,
    val englishTitle: String,
    val englishNative: String,
    val englishRomaji: String,
    val description: String,
    val staff: List<Staff>,
    val characters: List<Character>
)

data class Date(
    val year: Int,
    val month: Int,
    val day: Int
)