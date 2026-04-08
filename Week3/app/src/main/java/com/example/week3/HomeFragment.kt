package com.example.week3

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week3.ProductRepository
import com.example.week3.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        val productAdapter = ProductAdapter { clickedItem ->
            handleWishClick(clickedItem)
        }

        binding.recyclerViewHome.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            isNestedScrollingEnabled = false
        }

        loadProducts()
    }

    private fun loadProducts() {
        viewLifecycleOwner.lifecycleScope.launch {
            val allProducts = ProductRepository.getProductsOnce(requireContext())
            val adapter = binding.recyclerViewHome.adapter as ProductAdapter
            adapter.submitList(allProducts)
        }
    }

    private fun handleWishClick(clickedItem: ProductData) {
        val adapter = binding.recyclerViewHome.adapter as ProductAdapter
        val newList = adapter.currentList.toMutableList()

        val index = newList.indexOfFirst { it.id == clickedItem.id }
        if (index != -1) {
            newList[index] = clickedItem.copy(isWished = !clickedItem.isWished)

            adapter.submitList(newList)

            viewLifecycleOwner.lifecycleScope.launch {
                ProductRepository.saveProducts(requireContext(), newList)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}