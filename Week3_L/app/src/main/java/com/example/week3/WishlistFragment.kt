package com.example.week3

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week3.ProductRepository
import com.example.week3.databinding.FragmentWishlistBinding
import kotlinx.coroutines.launch

class WishlistFragment : Fragment(R.layout.fragment_wishlist) {
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWishlistBinding.bind(view)

        val productAdapter = ProductAdapter { clickedItem ->
            handleRemoveWish(clickedItem)
        }

        binding.recyclerWish.apply {
            adapter = productAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
        loadWishlist()
    }

    private fun loadWishlist() {
        viewLifecycleOwner.lifecycleScope.launch {
            val allProducts = ProductRepository.getProductsOnce(requireContext())
            val wishList = allProducts.filter { it.isWished }
            (binding.recyclerWish.adapter as ProductAdapter).submitList(wishList)
        }
    }

    private fun handleRemoveWish(clickedItem: ProductData) {
        viewLifecycleOwner.lifecycleScope.launch {
            val allProducts = ProductRepository.getProductsOnce(requireContext()).toMutableList()

            val index = allProducts.indexOfFirst { it.id == clickedItem.id }
            if (index != -1) {
                allProducts[index] = clickedItem.copy(isWished = false)

                ProductRepository.saveProducts(requireContext(), allProducts)

                val updatedWishList = allProducts.filter { it.isWished }
                (binding.recyclerWish.adapter as ProductAdapter).submitList(updatedWishList)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}