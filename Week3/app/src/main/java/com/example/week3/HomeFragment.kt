package com.example.week3

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home), ProductClickListener {

    private lateinit var dataStoreManager: DataStoreManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        dataStoreManager = DataStoreManager(requireContext())

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_home_products)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        viewLifecycleOwner.lifecycleScope.launch {
            dataStoreManager.getAllProducts().collect { productList ->

                if (productList.isNotEmpty()) {
                    recyclerView.adapter = ProductAdapter(productList, this@HomeFragment)
                }
            }
        }
    }


    override fun onProductClick(productName: String, price: String) {
        val purchaseFragment = PurchaseFragment()

        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container, purchaseFragment)
            .addToBackStack(null)
            .commit()
    }
}