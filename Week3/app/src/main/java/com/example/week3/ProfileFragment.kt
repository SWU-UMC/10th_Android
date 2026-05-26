package com.example.week3

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imgProfile = view.findViewById<ImageView>(R.id.imgProfile)
        val tvNickname = view.findViewById<TextView>(R.id.nickname)
        val rvFollowing = view.findViewById<RecyclerView>(R.id.rvFollowing)

        val service = ApiClient.service

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val profileResponse = service.getProfile()
                val user = profileResponse.data
                user?.let {
                    tvNickname.text = "${it.firstName} ${it.lastName}"
                    Glide.with(this@ProfileFragment).load(it.avatar).into(imgProfile)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "프로필 로드 실패", Toast.LENGTH_SHORT).show()
            }

            try {
                val followingResponse = service.getFollowingList(page = 1)
                val allUsers = followingResponse.data ?: listOf()

                val filteredUsers = allUsers.filter { it.id != 1 }

                val adapter = FollowingAdapter(filteredUsers)
                rvFollowing.adapter = adapter
                rvFollowing.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "팔로잉 목록 로드 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }
}