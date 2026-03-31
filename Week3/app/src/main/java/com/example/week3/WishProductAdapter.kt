package com.example.week3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WishProductAdapter (private val items: List<WishProduct>) :
    RecyclerView.Adapter<WishProductAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img = view.findViewById<ImageView>(R.id.imgProduct)
        val name = view.findViewById<TextView>(R.id.txtName)
        val desc = view.findViewById<TextView>(R.id.txtDesc)
        val color = view.findViewById<TextView>(R.id.txtColor)
        val price = view.findViewById<TextView>(R.id.txtPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_grid_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.img.setImageResource(item.imageRes)
        holder.name.text = item.name
        holder.desc.text = item.desc
        holder.color.text = "색상 ${item.colorCount}개"
        holder.price.text = item.price
    }

    override fun getItemCount() = items.size
}