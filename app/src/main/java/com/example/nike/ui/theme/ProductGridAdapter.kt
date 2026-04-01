package com.example.nike.ui.theme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.R

class ProductGridAdapter(
    private val productList: MutableList<Product>,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductGridAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProduct: ImageView = itemView.findViewById(R.id.imgProduct)
        val imgLike: ImageView = itemView.findViewById(R.id.imgLike)
        val tvBestSeller: TextView = itemView.findViewById(R.id.tvBestSeller)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val tvColorCount: TextView = itemView.findViewById(R.id.tvColorCount)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_grid, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        holder.imgProduct.setImageResource(product.imageResId)
        holder.tvName.text = product.name
        holder.tvDescription.text = product.description
        holder.tvColorCount.text = product.colorCount
        holder.tvPrice.text = product.price
        holder.tvBestSeller.visibility = if (product.isBestSeller) View.VISIBLE else View.GONE

        holder.imgLike.setImageResource(
            if (product.isLiked) R.drawable.ic_heart_filled
            else R.drawable.ic_heartstraight
        )

        holder.itemView.setOnClickListener {
            onItemClick(product)
        }

        holder.imgLike.setOnClickListener {
            product.isLiked = !product.isLiked

            holder.imgLike.setImageResource(
                if (product.isLiked) R.drawable.ic_heart_filled
                else R.drawable.ic_heartstraight
            )
        }
    }

    override fun getItemCount(): Int = productList.size
}