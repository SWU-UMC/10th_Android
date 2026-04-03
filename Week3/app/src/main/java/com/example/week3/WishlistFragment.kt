package com.example.week3 // 패키지명을 com.example.week3로 통일

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week3.R

// 1. ProductClickListener를 상속받아 "화살표(이동)" 기능을 구현합니다.
class WishlistFragment : Fragment(R.layout.fragment_wishlist), ProductClickListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 2. 위시리스트에 보여줄 데이터 (이미지 파일명 jordan1, socks 확인!)
        val wishList = listOf(
            Product("Air Jordan 1 Mid", "US$125", R.drawable.jordan1),
            Product("Nike Everyday Plus", "US$10", R.drawable.socks)
        )

        // 3. 리사이클러뷰 설정 (이미지처럼 2줄 격자로 보이게 GridLayoutManager 사용)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_wishlist_products)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        // 4. 어댑터 연결 (홈화면에서 쓴 ProductAdapter를 그대로 재사용!)
        recyclerView.adapter = ProductAdapter(wishList, this)
    }

    // 5. [핵심] 위시리스트 아이템 클릭 시 "구매하기(상세페이지)"로 이동하는 화살표 로직
    override fun onProductClick(productName: String, price: String) {
        val purchaseFragment = PurchaseFragment()

        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container, purchaseFragment)
            .addToBackStack(null)
            .commit()
    }
}