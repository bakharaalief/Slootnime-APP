package com.bakharaalief.graphqlapp.presentation.mediaDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bakharaalief.graphqlapp.R
import com.bakharaalief.graphqlapp.databinding.ItemCharacterBinding
import com.bakharaalief.graphqlapp.domain.model.Character
import com.bakharaalief.graphqlapp.domain.model.CharacterDiffUtil
import com.bumptech.glide.Glide

class CharactersAdapter :
    ListAdapter<Character, CharactersAdapter.MyViewHolder>(CharacterDiffUtil) {

    class MyViewHolder(private val itemCharacterBinding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(itemCharacterBinding.root) {

        fun bind(character: Character) {
            itemCharacterBinding.characterItemEnglishName.text = character.name

            Glide
                .with(itemView.context)
                .load(character.characterImage)
                .centerCrop()
                .placeholder(R.color.brown)
                .into(itemCharacterBinding.characterItemImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemCharacterBinding.inflate(inflater, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }
}