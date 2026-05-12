package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class BuyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.buyfragment, container, false)

        val btnAll = view.findViewById<Button>(R.id.btn_all)
        val btnTops = view.findViewById<Button>(R.id.btn_tops)
        val btnShoes = view.findViewById<Button>(R.id.btn_shoes)

        // 버튼 클릭 시 색상 변경 예시 (간단하게)
        btnAll.setOnClickListener {
            btnAll.setTextColor(resources.getColor(android.R.color.black))
            btnTops.setTextColor(resources.getColor(android.R.color.darker_gray))
            btnShoes.setTextColor(resources.getColor(android.R.color.darker_gray))
            // 여기서 "전체" 목록을 보여주는 로직 추가 가능
        }

        btnTops.setOnClickListener {
            btnTops.setTextColor(resources.getColor(android.R.color.black))
            btnAll.setTextColor(resources.getColor(android.R.color.darker_gray))
            btnShoes.setTextColor(resources.getColor(android.R.color.darker_gray))
            // 여기서 "Tops" 목록을 보여주는 로직 추가 가능
        }

        return view
    }
}