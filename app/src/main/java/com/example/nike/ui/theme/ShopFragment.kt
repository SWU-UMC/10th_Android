package com.example.nike.ui.theme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.R

class ShopFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_shop, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerShopProducts)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        recyclerView.adapter = ProductGridAdapter(ProductDummyData.getShopProducts()) { product ->
            moveToDetail(product)
        }

        return view
    }

    private fun moveToDetail(product: Product) {
        val bundle = Bundle().apply {
            putString("name", product.name)
            putString("price", product.price)
            putInt("image", product.imageResId)
        }

        val detailFragment = ProductDetailFragment()
        detailFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailFragment)
            .addToBackStack(null)
            .commit()
    }
}