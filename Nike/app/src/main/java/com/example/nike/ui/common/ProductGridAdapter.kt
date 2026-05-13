package com.example.nike.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.R
import com.example.nike.data.repository.ProductUiModel
import com.example.nike.databinding.ItemProductGridBinding

class ProductGridAdapter(
    private val onItemClick: (ProductUiModel) -> Unit,
    private val onHeartClick: (ProductUiModel) -> Unit
) : ListAdapter<ProductUiModel, ProductGridAdapter.ProductViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductGridBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductViewHolder(
        private val binding: ItemProductGridBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductUiModel) {
            binding.imgProduct.setImageResource(product.imageResId)
            binding.tvBestSeller.visibility = if (product.isBestSeller) View.VISIBLE else View.GONE
            binding.tvName.text = product.name
            binding.tvDescription.text = product.description
            binding.tvColorCount.text = "${product.colorCount} Colours"
            binding.tvPrice.text = "US$${product.price}"
            binding.imgLike.setImageResource(
                if (product.isLiked) R.drawable.ic_heart_filled else R.drawable.ic_heartstraight
            )
            binding.root.setOnClickListener { onItemClick(product) }
            binding.imgLike.setOnClickListener { onHeartClick(product) }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductUiModel>() {
            override fun areItemsTheSame(oldItem: ProductUiModel, newItem: ProductUiModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ProductUiModel, newItem: ProductUiModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}