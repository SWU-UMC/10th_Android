package com.example.week3

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week3.databinding.FragmentBuyAllBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuyAllFragment : Fragment(R.layout.fragment_buy_all) {
    private var _binding: FragmentBuyAllBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBuyAllBinding.bind(view)

        val productAdapter = ProductAdapter { clickedItem ->
            viewModel.toggleWishStatus(clickedItem.id)
        }

        binding.recyclerGrid.apply {
            adapter = productAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        viewModel.productList.observe(viewLifecycleOwner) { updatedList ->
            productAdapter.submitList(updatedList)
        }

        viewModel.fetchLocalProducts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}