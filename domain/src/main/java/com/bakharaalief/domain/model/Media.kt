package com.bakharaalief.domain.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class Media(
    val id: Int,
    val englishTitle: String,
    val englishNative: String,
    val englishRomaji: String,
    val coverImage: String,
    val episodes: Int,
    val genres: List<String>,
    val seasonYear: Int
) : Parcelable

object MediaDiffUtil : DiffUtil.ItemCallback<Media>() {
    override fun areItemsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem == newItem
    }
}