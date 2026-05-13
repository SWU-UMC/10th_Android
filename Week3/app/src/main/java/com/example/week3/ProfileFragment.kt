package com.example.week3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels // 중요
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.week3.FollowerAdapter
import com.example.week3.ShoppingViewModel
import com.example.week3.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!


    private val viewModel: ShoppingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.loadMyData(1)
        viewModel.loadFollowingData()


        viewModel.userData.observe(viewLifecycleOwner) { user ->
            user?.let {
                binding.tvProfileName.text = "${it.firstName} ${it.lastName}"
                Glide.with(this)
                    .load(it.avatar)
                    .circleCrop()
                    .into(binding.ivProfileUser)
            }
        }


        viewModel.followingList.observe(viewLifecycleOwner) { list ->
            if (list != null) {
                binding.rvFollowing.adapter = FollowerAdapter(list)
                binding.rvFollowing.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}