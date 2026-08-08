package com.example.week2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week2.databinding.ItemFollowingBinding

class FollowingAdapter : ListAdapter<UserData, FollowingAdapter.FollowingViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val binding = ItemFollowingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FollowingViewHolder(private val binding: ItemFollowingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserData) {
            Glide.with(binding.root.context)
                .load(user.avatar)
                .circleCrop()
                .into(binding.ivFollowingProfile)
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<UserData>() {
            override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean {
                return oldItem == newItem
            }
        }
    }
}
