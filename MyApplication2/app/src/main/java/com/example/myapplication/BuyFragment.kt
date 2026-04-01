package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BuyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // R.layout. 뒤의 이름을 실제 파일 이름인 buyfragment로 바꿉니다.
        return inflater.inflate(R.layout.buyfragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 2. [데이터 선언] 은지님이 만든 상품 리스트 4개
        val productList = arrayListOf(
            NikeItem("Nike Everyday Plus Cushioned", "Training Ankle Socks", "US$10"),
            NikeItem("Nike Elite Crew", "Basketball Socks", "US$16"),
            NikeItem("Nike Air Force 1 '07", "Women's Shoes", "US$115"),
            NikeItem("Jordan ENike Air Force 1 '07ssentials", "Men's Shoes", "US$115")
        )

        // 3. [RecyclerView 연결] XML에 만든 rv_buy를 찾아서 설정합니다.
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_buy)

        // 한 줄에 2개씩 보여주는 그리드 레이아웃 설정
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        // 어댑터 연결 (준비한 데이터를 어댑터에 던져줌)
        recyclerView.adapter = ProductAdapter(productList)
    }
}