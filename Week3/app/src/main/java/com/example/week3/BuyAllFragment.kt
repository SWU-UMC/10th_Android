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

        observeProducts()
    }

    private fun observeProducts() {
        viewLifecycleOwner.lifecycleScope.launch {
            ProductRepository.getProductsFlow(requireContext()).collect { updatedList ->
                (binding.recyclerGrid.adapter as ProductAdapter).submitList(updatedList)
            }
        }
    }

    private fun handleWishClick(clickedItem: ProductData) {
        viewLifecycleOwner.lifecycleScope.launch {
            val currentList = ProductRepository.getProductsOnce(requireContext()).toMutableList()
            val index = currentList.indexOfFirst { it.id == clickedItem.id }
            if (index != -1) {
                currentList[index] = clickedItem.copy(isWished = !clickedItem.isWished)

                ProductRepository.saveProducts(requireContext(), currentList)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}