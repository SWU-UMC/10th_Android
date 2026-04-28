package com.example.nike.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nike.R
import com.example.nike.databinding.FragmentHomeBinding
import com.example.nike.ui.product.ProductNavigator
import com.example.nike.ui.product.ProductViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductViewModel by activityViewModels()
    private lateinit var adapter: HomeProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        adapter = HomeProductAdapter(
            onHeartClick = { product -> viewModel.toggleWishlist(product.id) },
            onItemClick = { product -> (activity as? ProductNavigator)?.openProductDetail(product.id) }
        )

        binding.rvLatestProducts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvLatestProducts.adapter = adapter

        viewModel.allProducts.observe(viewLifecycleOwner) { products ->
            adapter.submitList(products.filter { it.isNew })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
