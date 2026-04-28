package com.example.week2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week2.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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
                val homeProducts = products.take(3) // 홈 화면에는 상위 3개만 표시 예시
                val adapter = ProductAdapter(
                    homeProducts,
                    onItemClick = { product ->
                        navigateToDetail(product)
                    },
                    onWishlistClick = { product, _ ->
                        viewLifecycleOwner.lifecycleScope.launch {
                            dataStoreManager.updateWishlistStatus(product.id, !product.isWishlisted)
                        }
                    }
                )

                binding.rvHomeProducts.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.rvHomeProducts.adapter = adapter
            }
        }
    }

    private fun navigateToDetail(product: Product) {
        val bundle = Bundle().apply {
            putParcelable("product", product)
        }
        findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
