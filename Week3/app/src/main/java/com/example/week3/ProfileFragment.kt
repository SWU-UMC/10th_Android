package com.example.week3

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imgProfile = view.findViewById<ImageView>(R.id.imgProfile)
        val tvNickname = view.findViewById<TextView>(R.id.nickname)
        val rvFollowing = view.findViewById<RecyclerView>(R.id.rvFollowing)

        viewModel.userProfile.observe(viewLifecycleOwner) { user ->
            tvNickname.text = "${user.firstName} ${user.lastName}"
            Glide.with(this).load(user.avatar).into(imgProfile)
        }

        viewModel.userList.observe(viewLifecycleOwner) { users ->
            rvFollowing.adapter = FollowingAdapter(users)
            rvFollowing.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        viewModel.fetchProfileData()
    }
}