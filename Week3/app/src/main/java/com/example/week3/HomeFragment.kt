package com.example.week3

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment(R.layout.fragment_home), ProductClickListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productList = listOf(
            Product("AirJordan1", "$115", R.drawable.jordan1),
            Product("AirJordan2", "$120", R.drawable.jordan2)
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_home_products)

        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        recyclerView.adapter = ProductAdapter(productList, this)
    }

    override fun onProductClick(productName: String, price: String) {
        val purchaseFragment = PurchaseFragment()

        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container, purchaseFragment)
            .addToBackStack(null) // 뒤로가기 버튼 지원
            .commit()
    }
}