package com.example.myapplication // 👈 은지님 패키지명

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
// R 패키지 임포트는 본인 프로젝트명에 맞게 확인!

class ProductAdapter(private val itemList: ArrayList<NikeItem>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val tvCategory: TextView = view.findViewById(R.id.tv_category)
        val tvPrice: TextView = view.findViewById(R.id.tv_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        // 여기서 바뀐 이름들(prodName 등)을 연결합니다
        holder.ivLike.setOnClickListener {
            item.isLiked = !item.isLiked // 하트 상태 반전

            // 💡 여기서 위시리스트 매니저를 불러서 데이터를 넣었다 뺐다 하는 거예요!
            WishlistManager.toggleWish(item)

            // 하트 모양을 새로고침 (빨간 하트 <-> 빈 하트)
            notifyItemChanged(position)

        holder.tvName.text = item.prodName
        holder.tvCategory.text = item.prodCategory
        holder.tvPrice.text = item.prodPrice

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            android.widget.Toast.makeText(context, "${item.prodName}을 선택하셨습니다!", android.widget.Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = itemList.size
}