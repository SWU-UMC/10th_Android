package com.example.nike.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nike.databinding.ItemFollowingUserBinding

class FollowingUserAdapter : ListAdapter<ProfileUserUiModel, FollowingUserAdapter.FollowingViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val binding = ItemFollowingUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FollowingViewHolder(
        private val binding: ItemFollowingUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: ProfileUserUiModel) {
            Glide.with(binding.root)
                .load(user.avatarUrl)
                .circleCrop()
                .into(binding.ivFollowingAvatar)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProfileUserUiModel>() {
            override fun areItemsTheSame(
                oldItem: ProfileUserUiModel,
                newItem: ProfileUserUiModel
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ProfileUserUiModel,
                newItem: ProfileUserUiModel
            ): Boolean = oldItem == newItem
        }
    }
}