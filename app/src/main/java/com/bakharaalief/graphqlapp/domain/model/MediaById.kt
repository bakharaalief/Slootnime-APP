package com.bakharaalief.graphqlapp.domain.model

data class MediaById(
    val date: Date,
    val bannerImage: String,
    val englishTitle: String,
    val englishNative: String,
    val englishRomaji: String,
    val description: String,
    val staff: List<Staff>
)

data class Date(
    val year: Int,
    val month: Int,
    val day: Int
)

data class Staff(
    val name: String,
    val staffImage: String
)
