package com.bakharaalief.graphqlapp.util

import com.bakharaalief.app.ListMediaQuery
import com.bakharaalief.app.MediaByIdQuery
import com.bakharaalief.graphqlapp.domain.model.*

object DataMapper {

    fun List<ListMediaQuery.Medium>.toMediaModel(): List<Media> {
        return this.map {
            Media(
                it.id,
                it.title?.english ?: "not found",
                it.title?.native ?: "not found",
                it.title?.romaji ?: "not found",
                it.coverImage?.extraLarge ?: "not found",
                it.episodes ?: 0,
                it.genres?.filterNotNull() ?: emptyList(),
                it.seasonYear ?: 0
            )
        }
    }

    fun MediaByIdQuery.Medium.toMediaByIdModel(): MediaById {
        val date = Date(
            this.startDate?.year ?: 0,
            this.startDate?.month ?: 0,
            this.startDate?.day ?: 0
        )

        return MediaById(
            date,
            this.bannerImage ?: "not found",
            this.title?.english ?: "not found",
            this.title?.native ?: "not found",
            this.title?.romaji ?: "not found",
            this.description ?: "not found",
            this.staff?.nodes?.filterNotNull()?.toStaffModel() ?: emptyList(),
            this.characters?.nodes?.filterNotNull()?.toCharacterModel() ?: emptyList()
        )
    }

    private fun List<MediaByIdQuery.Node>.toStaffModel(): List<Staff> {
        return this.map {
            Staff(
                it.id,
                it.name?.full ?: "not found",
                it.image?.large ?: "not found"
            )
        }
    }

    private fun List<MediaByIdQuery.Node1>.toCharacterModel(): List<Character> {
        return this.map {
            Character(
                it.id,
                it.name?.full ?: "not found",
                it.image?.large ?: "not found"
            )
        }
    }
}