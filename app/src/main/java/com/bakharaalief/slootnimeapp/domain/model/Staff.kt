package com.bakharaalief.slootnimeapp.domain.model

import androidx.recyclerview.widget.DiffUtil

data class Staff(
    val id: Int,
    val name: String,
    val staffImage: String
)

object StaffDiffUtil : DiffUtil.ItemCallback<Staff>() {
    override fun areItemsTheSame(oldItem: Staff, newItem: Staff): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Staff, newItem: Staff): Boolean {
        return oldItem == newItem
    }
}