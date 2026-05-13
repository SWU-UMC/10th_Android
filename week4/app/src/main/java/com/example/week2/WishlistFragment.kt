package com.example.week2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week2.databinding.FragmentWishlistBinding
import kotlinx.coroutines.launch

class WishlistFragment : Fragment() {
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataStoreManager = DataStoreManager(requireContext())
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        viewLifecycleOwner.lifecycleScope.launch {
            dataStoreManager.productsFlow.collect { products ->
                val wishlistProducts = products.filter { it.isWishlisted }
                val adapter = ProductAdapter(
                    wishlistProducts,
                    onItemClick = { product ->
                        navigateToDetail(product)
                    },
                    onWishlistClick = { product, _ ->
                        viewLifecycleOwner.lifecycleScope.launch {
                            dataStoreManager.updateWishlistStatus(product.id, !product.isWishlisted)
                        }
                    }
                )

                binding.rvWishlistProducts.layoutManager = GridLayoutManager(context, 2)
                binding.rvWishlistProducts.adapter = adapter
            }
        }
    }

    private fun navigateToDetail(product: Product) {
        val bundle = Bundle().apply {
            putParcelable("product", product)
        }
        findNavController().navigate(R.id.action_wishlistFragment_to_productDetailFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
