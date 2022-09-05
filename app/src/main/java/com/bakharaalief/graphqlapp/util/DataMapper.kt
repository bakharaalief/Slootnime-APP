package com.bakharaalief.graphqlapp.util

import com.bakharaalief.app.CharactersByIdsQuery
import com.bakharaalief.app.CharactersQuery
import com.bakharaalief.graphqlapp.domain.model.Character
import com.bakharaalief.graphqlapp.domain.model.CharacterById

object DataMapper {

    fun List<CharactersQuery.Result>.toCharacterModel(): List<Character> {
        return this.map {
            Character(
                it.id ?: "null",
                it.name ?: "tidak ada nama",
                it.image ?: "tidak ada image"
            )
        }
    }

    fun List<CharactersByIdsQuery.CharactersById>.toCharacterIdModel(): List<CharacterById> {
        return this.map {
            CharacterById(
                it.name ?: "tidak ada nama",
                it.gender ?: "tidak ada gender",
                it.species ?: "tidak ada species"
            )
        }
    }
}