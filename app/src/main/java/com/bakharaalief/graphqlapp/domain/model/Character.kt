package com.bakharaalief.graphqlapp.domain.model

import androidx.recyclerview.widget.DiffUtil

data class Character(
    val id: String,
    val name: String,
    val image: String
)

object CharacterDiffUtil : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}