package com.example.week3

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week3.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        val productAdapter = ProductAdapter { clickedItem ->
            viewModel.toggleWishStatus(clickedItem.id)
        }

        binding.recyclerViewHome.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            isNestedScrollingEnabled = false
        }

        viewModel.productList.observe(viewLifecycleOwner) { allProducts ->
            productAdapter.submitList(allProducts)
        }
        viewModel.fetchLocalProducts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}