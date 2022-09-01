package com.bakharaalief.graphqlapp.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bakharaalief.app.CharactersQuery
import com.bakharaalief.graphqlapp.R
import com.bakharaalief.graphqlapp.databinding.ItemMediaBinding
import com.bumptech.glide.Glide

class CharactersAdapter(private val onItemClick: (id: String, name: String, image: String) -> Unit) :
    PagingDataAdapter<CharactersQuery.Result, CharactersAdapter.MyViewHolder>(
        DIFF_CALLBACK
    ) {

    class MyViewHolder(private val itemMediaBinding: ItemMediaBinding) :
        RecyclerView.ViewHolder(itemMediaBinding.root) {

        val mediaItemCard = itemMediaBinding.mediaItemCard

        fun bind(charactersQuery: CharactersQuery.Result) {
            itemMediaBinding.mediaItemEnglishTitle.text = charactersQuery.name

            Glide
                .with(itemView.context)
                .load(charactersQuery.image)
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
            onItemClick(data?.id ?: "0", data?.name ?: "0", data?.image ?: "0")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemMediaBinding.inflate(inflater, parent, false)
        return MyViewHolder(view)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CharactersQuery.Result>() {
            override fun areItemsTheSame(
                oldItem: CharactersQuery.Result,
                newItem: CharactersQuery.Result
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CharactersQuery.Result,
                newItem: CharactersQuery.Result
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}