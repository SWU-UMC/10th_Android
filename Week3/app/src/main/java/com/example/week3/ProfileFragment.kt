package com.example.week3

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imgProfile = view.findViewById<ImageView>(R.id.imgProfile)
        val tvNickname = view.findViewById<TextView>(R.id.nickname)
        val rvFollowing = view.findViewById<RecyclerView>(R.id.rvFollowing)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("x-api-key", "reqres_286fae62df9643f089a91de5a72a2ef4")
                    .method(original.method, original.body)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ReqResService::class.java)

        service.getProfile().enqueue(object : Callback<SingleUserResponse> {
            override fun onResponse(call: Call<SingleUserResponse>, response: Response<SingleUserResponse>) {
                if (response.isSuccessful) {
                    val user = response.body()?.data
                    user?.let {
                        tvNickname.text = "${it.firstName} ${it.lastName}"
                        Glide.with(this@ProfileFragment).load(it.avatar).into(imgProfile)
                    }
                }
            }
            override fun onFailure(call: Call<SingleUserResponse>, t: Throwable) {}
        })

        service.getFollowingList().enqueue(object : Callback<UserListResponse> {
            override fun onResponse(call: Call<UserListResponse>, response: Response<UserListResponse>) {
                if (response.isSuccessful) {
                    val allUsers = response.body()?.data ?: listOf()

                    val filteredUsers = allUsers.filter { it.id != 1 }

                    val adapter = FollowingAdapter(filteredUsers)
                    rvFollowing.adapter = adapter
                    rvFollowing.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                }
            }

            override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                Toast.makeText(context, "목록 로드 실패", Toast.LENGTH_SHORT).show()
            }
        })
    }
}