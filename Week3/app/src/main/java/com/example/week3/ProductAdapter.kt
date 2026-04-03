package com.example.week3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private val items: List<Product>,
    private val listener: ProductClickListener // Delegate
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img = view.findViewById<ImageView>(R.id.item_image)
        val name = view.findViewById<TextView>(R.id.item_name)
        val price = view.findViewById<TextView>(R.id.item_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_horizontal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.name
        holder.price.text = item.price
        holder.img.setImageResource(item.imageRes)

        // 클릭 이벤트 전달
        holder.itemView.setOnClickListener {
            listener.onProductClick(item.name, item.price)
        }
    }

    override fun getItemCount() = items.size
}