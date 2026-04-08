package com.example.nike.ui.theme

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nike.R
import com.example.nike.databinding.FragmentSaleBinding
import com.example.nike.ui.theme.ProductViewModel
import com.example.nike.ui.theme.ProductAdapter
import com.example.nike.ui.theme.ProductDetailFragment

class SaleFragment : Fragment(R.layout.fragment_sale) {

    private var _binding: FragmentSaleBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductViewModel by activityViewModels()
    private lateinit var adapter: ProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSaleBinding.bind(view)

        adapter = ProductAdapter(
            onHeartClick = { product ->
                viewModel.toggleWishlist(product.id)
            },
            onItemClick = { product ->
                val intent = Intent(requireContext(), ProductDetailFragment::class.java)
                intent.putExtra("product_id", product.id)
                startActivity(intent)
            }
        )

        binding.rvSale.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvSale.adapter = adapter

        viewModel.allProducts.observe(viewLifecycleOwner) { products ->
            adapter.submitList(products.filter { it.category == "SALE" })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}