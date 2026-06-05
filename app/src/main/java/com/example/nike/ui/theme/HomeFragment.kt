package com.example.nike.ui.theme

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nike.R
import com.example.nike.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeProductAdapter: HomeProductAdapter
    private val viewModel: ProductViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        homeProductAdapter = HomeProductAdapter(
            onHeartClick = { product ->
                viewModel.toggleWishlist(product.id)
            },
            onItemClick = { product ->
                val intent = Intent(requireContext(), ProductDetailActivity::class.java)
                intent.putExtra("product_id", product.id)
                startActivity(intent)
            }
        )

        binding.rvLatestProducts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvLatestProducts.adapter = homeProductAdapter

        viewModel.allProducts.observe(viewLifecycleOwner) { products ->
            homeProductAdapter.submitList(products.filter { it.isNew })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
