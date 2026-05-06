package com.example.nike.ui.shop

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nike.R
import com.example.nike.databinding.FragmentSaleBinding
import com.example.nike.ui.common.ProductGridAdapter
import com.example.nike.ui.product.ProductNavigator
import com.example.nike.ui.product.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SaleFragment : Fragment(R.layout.fragment_sale) {

    private var _binding: FragmentSaleBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductViewModel by activityViewModels()
    private lateinit var adapter: ProductGridAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSaleBinding.bind(view)

        adapter = ProductGridAdapter(
            onItemClick = { product -> (activity as? ProductNavigator)?.openProductDetail(product.id) },
            onHeartClick = { product -> viewModel.toggleWishlist(product.id) }
        )

        binding.rvSale.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvSale.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allProducts.collect { products ->
                    adapter.submitList(products.filter { it.category == CATEGORY_SALE })
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val CATEGORY_SALE = "SALE"
    }
}
