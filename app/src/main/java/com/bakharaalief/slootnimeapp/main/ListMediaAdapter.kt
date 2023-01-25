package com.bakharaalief.slootnimeapp.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bakharaalief.slootnimeapp.R
import com.bakharaalief.slootnimeapp.databinding.ItemMediaBinding
import com.bakharaalief.domain.model.Media
import com.bakharaalief.domain.model.MediaDiffUtil
import com.bumptech.glide.Glide

class ListMediaAdapter(private val onItemClick: (media: Media) -> Unit) :
    PagingDataAdapter<Media, ListMediaAdapter.MyViewHolder>(MediaDiffUtil) {

    class MyViewHolder(private val itemMediaBinding: ItemMediaBinding) :
        RecyclerView.ViewHolder(itemMediaBinding.root) {

        val mediaItemCard = itemMediaBinding.mediaItemCard

        fun bind(media: Media) {
            itemMediaBinding.mediaItemEnglishTitle.text = media.englishTitle

            Glide
                .with(itemView.context)
                .load(media.coverImage)
                .centerCrop()
                .placeholder(R.color.brown)
                .into(itemMediaBinding.mediaItemCover)
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }

        holder.mediaItemCard.setOnClickListener {
            onItemClick(data ?: Media(0, "", "", "", "", 0, emptyList(), 0))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemMediaBinding.inflate(inflater, parent, false)
        return MyViewHolder(view)
    }
}