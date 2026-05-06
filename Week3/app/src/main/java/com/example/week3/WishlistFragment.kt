package com.example.week3

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week3.databinding.FragmentWishlistBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels

@AndroidEntryPoint
class WishlistFragment : Fragment(R.layout.fragment_wishlist) {
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWishlistBinding.bind(view)

        val productAdapter = ProductAdapter { clickedItem ->
            viewModel.toggleWishStatus(clickedItem.id)
        }

        binding.recyclerWish.apply {
            adapter = productAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        viewModel.productList.observe(viewLifecycleOwner) { allProducts ->
            val wishList = allProducts.filter { it.isWished }
            productAdapter.submitList(wishList)
        }

        viewModel.fetchLocalProducts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}