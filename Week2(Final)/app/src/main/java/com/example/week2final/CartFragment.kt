package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.week2final.R
class CartFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 이 프래그먼트는 fragment_cart.xml 디자인을 사용합니다.
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }
}