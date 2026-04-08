package com.example.week3

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week3.ProductRepository
import com.example.week3.databinding.FragmentBuyAllBinding
import kotlinx.coroutines.launch

class BuyAllFragment : Fragment(R.layout.fragment_buy_all) {
    private var _binding: FragmentBuyAllBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBuyAllBinding.bind(view)

        val productAdapter = ProductAdapter { clickedItem ->
            handleWishClick(clickedItem)
        }

        binding.recyclerGrid.apply {
            adapter = productAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
        loadProducts()
    }

    private fun loadProducts() {
        viewLifecycleOwner.lifecycleScope.launch {
            val allProducts = ProductRepository.getProductsOnce(requireContext())

            (binding.recyclerGrid.adapter as ProductAdapter).submitList(allProducts)
        }
    }

    private fun handleWishClick(clickedItem: ProductData) {
        val adapter = binding.recyclerGrid.adapter as ProductAdapter
        val currentList = adapter.currentList.toMutableList()
        val index = currentList.indexOfFirst { it.id == clickedItem.id }
        if (index != -1) {
            currentList[index] = clickedItem.copy(isWished = !clickedItem.isWished)
            adapter.submitList(currentList)

            viewLifecycleOwner.lifecycleScope.launch {
                ProductRepository.saveProducts(requireContext(), currentList)
            }
        }
    }
}