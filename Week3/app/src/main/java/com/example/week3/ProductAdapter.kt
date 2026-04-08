package com.example.week3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.week3.databinding.ItemGridProductBinding


class ProductAdapter(private val onWishClick: (ProductData) -> Unit
) : ListAdapter<ProductData, ProductAdapter.ViewHolder>(ProductDiffCallback()) {

    inner class ViewHolder(val binding: ItemGridProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGridProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            imgProduct.setImageResource(item.imageRes)
            txtName.text = item.name
            txtDesc.text = item.desc
            txtColor.text = "색상 ${item.colorCount}개"
            txtPrice.text = item.price

            btnWish.setImageResource(
                if (item.isWished) R.drawable.ic_wish_heart else R.drawable.ic_wish_heart
            )

            btnWish.setOnClickListener {
                onWishClick(item)
            }
        }
    }
}

class ProductDiffCallback : DiffUtil.ItemCallback<ProductData>() {
    override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
        return oldItem == newItem
    }
}