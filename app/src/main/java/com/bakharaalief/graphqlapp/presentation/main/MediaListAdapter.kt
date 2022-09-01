package com.bakharaalief.graphqlapp.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bakharaalief.app.MediaListQuery
import com.bakharaalief.graphqlapp.R
import com.bakharaalief.graphqlapp.databinding.ItemMediaBinding
import com.bumptech.glide.Glide

class MediaListAdapter : ListAdapter<MediaListQuery.Medium, MediaListAdapter.MyViewHolder>(
    DIFF_CALLBACK
) {

    class MyViewHolder(private val itemMediaBinding: ItemMediaBinding) :
        RecyclerView.ViewHolder(itemMediaBinding.root) {

        fun bind(mediaListQuery: MediaListQuery.Medium) {
            itemMediaBinding.mediaItemEnglishTitle.text = mediaListQuery.title?.english ?: "kosong"

            Glide
                .with(itemView.context)
                .load(mediaListQuery.coverImage?.large)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemMediaBinding.mediaItemCover)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemMediaBinding.inflate(inflater, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MediaListQuery.Medium>() {
            override fun areItemsTheSame(
                oldItem: MediaListQuery.Medium,
                newItem: MediaListQuery.Medium
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MediaListQuery.Medium,
                newItem: MediaListQuery.Medium
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}