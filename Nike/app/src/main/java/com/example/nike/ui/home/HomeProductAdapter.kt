package com.example.nike.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.R
import com.example.nike.data.repository.ProductUiModel
import com.example.nike.databinding.ItemProductBinding

class HomeProductAdapter(
    private val onHeartClick: (ProductUiModel) -> Unit,
    private val onItemClick: (ProductUiModel) -> Unit
) : RecyclerView.Adapter<HomeProductAdapter.ProductViewHolder>() {

    private val items = mutableListOf<ProductUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun submitList(newItems: List<ProductUiModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductUiModel) {
            binding.ivProduct.setImageResource(item.imageResId)
            binding.tvProductName.text = item.name
            binding.tvProductPrice.text = "US$${item.price}"
            binding.ivHeart.setImageResource(
                if (item.isLiked) R.drawable.ic_heart_filled else R.drawable.ic_heartstraight
            )
            binding.ivHeart.setOnClickListener { onHeartClick(item) }
            binding.root.setOnClickListener { onItemClick(item) }
        }
    }
}
