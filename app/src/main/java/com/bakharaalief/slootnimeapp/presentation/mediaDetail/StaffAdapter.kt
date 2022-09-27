package com.bakharaalief.slootnimeapp.presentation.mediaDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bakharaalief.slootnimeapp.R
import com.bakharaalief.slootnimeapp.databinding.ItemStaffBinding
import com.bakharaalief.slootnimeapp.domain.model.Staff
import com.bakharaalief.slootnimeapp.domain.model.StaffDiffUtil
import com.bumptech.glide.Glide

class StaffAdapter : ListAdapter<Staff, StaffAdapter.MyViewHolder>(StaffDiffUtil) {

    class MyViewHolder(private val itemStaffBinding: ItemStaffBinding) :
        RecyclerView.ViewHolder(itemStaffBinding.root) {

        fun bind(staff: Staff) {
            itemStaffBinding.staffItemEnglishName.text = staff.name

            Glide
                .with(itemView.context)
                .load(staff.staffImage)
                .centerCrop()
                .placeholder(R.color.brown)
                .into(itemStaffBinding.staffItemImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemStaffBinding.inflate(inflater, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }
}