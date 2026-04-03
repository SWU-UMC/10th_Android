package com.example.week3

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// ProductClickListener를 상속받아 클릭 이벤트를 직접 처리합니다 (Delegate 패턴)
class HomeFragment : Fragment(R.layout.fragment_home), ProductClickListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. 화면에 뿌려줄 데이터 리스트 만들기
        val productList = listOf(
            Product("AirJordan1", "$115", R.drawable.jordan1),
            Product("AirJordan2", "$120", R.drawable.jordan2)
        )

        // 2. 리사이클러뷰 찾아오기
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_home_products)

        // 3. 리사이클러뷰가 가로(Horizontal)로 흐르도록 설정
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // 4. 어댑터 연결 (this는 클릭 리스너 역할을 하는 HomeFragment 자신을 의미합니다)
        recyclerView.adapter = ProductAdapter(productList, this)
    }

    // 아이템 클릭 시 실행될 함수 (워크북 미션 핵심!)
    override fun onProductClick(productName: String, price: String) {
        // 클릭 시 PurchaseFragment(구매 페이지)로 이동하는 로직
        val purchaseFragment = PurchaseFragment()

        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container, purchaseFragment)
            .addToBackStack(null) // 뒤로가기 버튼 지원
            .commit()
    }
}