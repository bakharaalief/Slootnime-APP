package com.bakharaalief.domain.model

import androidx.recyclerview.widget.DiffUtil

data class Character(
    val id: Int,
    val name: String,
    val characterImage: String
)

object CharacterDiffUtil : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}