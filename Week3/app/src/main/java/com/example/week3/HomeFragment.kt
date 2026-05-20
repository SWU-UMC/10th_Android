package com.example.week3

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), ProductClickListener {

    private val viewModel: ShoppingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_home_products)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        viewModel.allProducts.observe(viewLifecycleOwner) { productList ->
            if (!productList.isNullOrEmpty()) {
                recyclerView.adapter = ProductAdapter(productList, this)
            }
        }
    }

    override fun onProductClick(productName: String, price: String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container, PurchaseFragment())
            .addToBackStack(null)
            .commit()
    }
}