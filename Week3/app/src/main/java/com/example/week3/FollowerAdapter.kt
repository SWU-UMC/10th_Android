package com.example.week3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week3.databinding.ItemFollowerBinding // ViewBinding 사용 가정

class FollowerAdapter(private val users: List<UserInfo>) : RecyclerView.Adapter<FollowerAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemFollowerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        Glide.with(holder.itemView.context).load(user.avatar).into(holder.binding.ivFollowerProfile)
    }

    override fun getItemCount() = users.size
}