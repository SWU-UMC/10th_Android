package com.example.week3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WishlistFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_wishlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerWish)

        val productList = listOf(
            WishProduct(R.drawable.image2, "상품2", "설명2", 2, "₩40,000"),
            WishProduct(R.drawable.image3, "상품3", "설명3", 4, "₩80,000")
        )

        recyclerView.adapter = WishProductAdapter(productList)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

    }
}