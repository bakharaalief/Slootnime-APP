package com.bakharaalief.graphqlapp.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bakharaalief.graphqlapp.R
import com.bakharaalief.graphqlapp.databinding.ItemMediaBinding
import com.bakharaalief.graphqlapp.domain.model.Media
import com.bakharaalief.graphqlapp.domain.model.MediaDiffUtil
import com.bumptech.glide.Glide

class CharactersAdapter(private val onItemClick: (id: Int) -> Unit) :
    PagingDataAdapter<Media, CharactersAdapter.MyViewHolder>(MediaDiffUtil) {

    class MyViewHolder(private val itemMediaBinding: ItemMediaBinding) :
        RecyclerView.ViewHolder(itemMediaBinding.root) {

        val mediaItemCard = itemMediaBinding.mediaItemCard

        fun bind(media: Media) {
            itemMediaBinding.mediaItemEnglishTitle.text = media.englishTitle

            Glide
                .with(itemView.context)
                .load(media.coverImage)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemMediaBinding.mediaItemCover)
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }

        holder.mediaItemCard.setOnClickListener {
            onItemClick(data?.id ?: 0)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemMediaBinding.inflate(inflater, parent, false)
        return MyViewHolder(view)
    }
}