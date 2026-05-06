package com.example.week3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FollowingAdapter(private val userList: List<UserData>) :
    RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_following, parent, false)
        return FollowingViewHolder(view)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        val user = userList[position]
        holder.name.text = user.firstName

        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .into(holder.image)
    }

    override fun getItemCount(): Int = userList.size

    class FollowingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.ivFollowingProfile)
        val name: TextView = view.findViewById(R.id.tvFollowingName)
    }
}