package com.example.week3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Tab1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_tab1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerGrid)

        val productList = listOf(
            GridProduct(R.drawable.image4, "상품1", "설명1", 1, "₩20,000"),
            GridProduct(R.drawable.image2, "상품2", "설명2", 2, "₩40,000"),
            GridProduct(R.drawable.image3, "상품3", "설명3", 4, "₩80,000"),
            GridProduct(R.drawable.image1, "상품4", "설명4", 5, "₩100,000"),
        )

        recyclerView.adapter = GridProductAdapter(productList)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
    }
}